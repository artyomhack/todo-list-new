package my.todo.presenter;

import my.todo.domain.common.DomainError;
import my.todo.domain.common.Either;
import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.models.user.User;
import my.todo.domain.models.user.UserRequest;
import my.todo.domain.storage.task.TaskInteractor;
import my.todo.domain.storage.user.UserInteractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping( "/users" )
public class UserController {

    private final UserInteractor userInteractor;
    private final TaskInteractor taskInteractor;

    public UserController( UserInteractor userInteractor, TaskInteractor taskInteractor ) {
        this.userInteractor = userInteractor;
        this.taskInteractor = taskInteractor;
    }

    @GetMapping( "/{id:[0-9]+}" )
    public ModelAndView getUser( @PathVariable String id, ModelAndView model ) {
        return showUserForm( userInteractor.fetchById( Integer.parseInt( id ) ) );
    }

    @GetMapping( "/" )
    public ModelAndView showCreateUser() {
        return showUserForm( new Either<>( null, null ) );
    }

    @PostMapping( value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public ModelAndView showCreateUser( UserRequest.Data request ) {
        var user = userInteractor.create( request );

        if ( user.hasError() || Objects.isNull( user.getData() ) ) {
            return showUserForm( user );
        }

        return new ModelAndView( "redirect:/users/" + user.getData().getId() );
    }

    @PostMapping( value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public ModelAndView updateUser( @PathVariable String id, UserRequest.Data request ) {
        var user = userInteractor.update( Integer.parseInt( id ), request );

        return showUserForm( user );

    }

    @GetMapping( "/delete/{id:[0-9]+}" )
    public ModelAndView removeUser( @PathVariable String id ) {
        var user = userInteractor.removeById( Integer.parseInt( id ) );

        if ( user.getData() ) {
            return new ModelAndView( "redirect:/users/");
        }

        return errorPage( HttpStatus.BAD_REQUEST );
    }

    @GetMapping( "/list" )
    public ModelAndView showList() {
        var data = userInteractor.fetchAll();

        if ( data.hasError()) {
            return errorPage( HttpStatus.BAD_REQUEST );
        }

        var listView = new ModelAndView();
        listView.setViewName( "user_list" );
        listView.getModelMap().addAttribute( "users", data.getData() );

        return listView;
    }

    @GetMapping("/list/delete/{id:[0-9]+}")
    public ModelAndView removeById( @PathVariable String id ) {
        var result = userInteractor.removeById(Integer.parseInt(id));

        if (result.getData()) {
            return new ModelAndView("redirect:/users/list");
        }

        return errorPage(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId:[0-9]+}/deleteTask/{taskId:[0-9]+}")
    public ModelAndView deleteTaskForUser(@PathVariable String userId, @PathVariable String taskId) {
        var result = userInteractor.removeTaskForUser(Integer.parseInt(userId), Integer.parseInt(taskId));

        if (result.getData()) {
            return new ModelAndView("redirect:/users/" + userId);
        }

        return errorPage(HttpStatus.BAD_REQUEST);
    }

    @PostMapping( value = "/{userId:[0-9]+}/createTask", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public ModelAndView createTaskForUser(@PathVariable String userId, TaskRequest.Data request ) {
        request.setId(null);

        var user = userInteractor.addTaskToUser(Integer.parseInt(userId), request);

        if (!user.hasError()) {
            return new ModelAndView("redirect:/users/" + userId);
        }

        return errorPage(HttpStatus.BAD_REQUEST);
    }

    private ModelAndView showUserForm( Either<DomainError, User.Details> user ) {
        var model = new ModelAndView();
        var data = user.getData();
        var taskList = taskInteractor.fetchAll().getData();


        if ( user.hasError()) {
            return errorPage( HttpStatus.BAD_REQUEST );

        }

        if ( Objects.nonNull(data) ) {
            model.getModelMap().addAttribute( "id", user.getData().getId() );
            model.getModelMap().addAttribute( "firstName", user.getData().getFirstName() );
            model.getModelMap().addAttribute( "middleName", user.getData().getMiddleName() );
            model.getModelMap().addAttribute( "lastName", user.getData().getLastName() );
            model.getModelMap().addAttribute("tasks", user.getData().getTasks());
            model.getModelMap().addAttribute("task_list", taskList);
        }

        model.setViewName( "user_form" );
        return model;
    }

    private ModelAndView errorPage( HttpStatus status ) {
        var redirectModel = new ModelAndView();

        redirectModel.setStatus( status );

        var errorTitle = "Error: " + status.name() + " " + "Status: " + status.value();

        redirectModel.getModelMap().addAttribute( "errorTitle", errorTitle );
        redirectModel.setViewName( "error_page" );
        return redirectModel;
    }

}
