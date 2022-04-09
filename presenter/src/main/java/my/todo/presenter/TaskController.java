package my.todo.presenter;

import my.todo.domain.common.DomainError;
import my.todo.domain.common.Either;
import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.storage.task.TaskInteractor;
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
@RequestMapping( "/tasks" )
public class TaskController {

    private final TaskInteractor interactor;

    public TaskController( TaskInteractor interactor) {
        this.interactor = interactor;
    }

    @GetMapping( "/{id:[0-9]+}" )
    public ModelAndView getTask( @PathVariable String id, ModelAndView model ) {
        var intId = Integer.parseInt( id );
        var task = interactor.fetchById( intId );
        return showTaskForm( task );
    }

    @GetMapping( value = "/" )
    public ModelAndView showCreateTask() {
        return showTaskForm( new Either<>( null, null ) );
    }

    @PostMapping( value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public ModelAndView showCreateTask( TaskRequest.Data request ) {
        var task = interactor.create( request );

        if ( task.hasError() || Objects.isNull( task.getData() ) ) {
            return showTaskForm( task );
        }

        return new ModelAndView( "redirect:/tasks/" + task.getData().getId() );
    }

    @PostMapping( value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public ModelAndView updateTask( @PathVariable String id, TaskRequest.Data request ) {
        var task = interactor.update( Integer.parseInt( id ), request );
        return showTaskForm( task );
    }

    @GetMapping( "/delete/{id:[0-9]+}" )
    public ModelAndView removeTask( @PathVariable String id ) {
        var result = interactor.removeById( Integer.parseInt( id ) );
        if ( result.getData() ) {
            return new ModelAndView( "redirect:/tasks/" );
        }

        return errorPage( HttpStatus.BAD_REQUEST );
    }

    @GetMapping( "/list" )
    public ModelAndView showList() {
        var data = interactor.fetchAll();

        if ( data.hasError() ) {
            return errorPage( HttpStatus.BAD_REQUEST );
        }

        var listView = new ModelAndView();
        listView.setViewName( "task_list" );
        listView.getModelMap().addAttribute( "tasks", data.getData() );
        return listView;
    }

    @GetMapping("/list/delete/{id:[0-9]+}")
    public ModelAndView deleteTaskById( @PathVariable String id ) {
        var result = interactor.removeById(Integer.parseInt(id));
        if (result.getData()) {
            return new ModelAndView("redirect:/tasks/list");
        }

        return errorPage(HttpStatus.BAD_REQUEST);
    }


    private ModelAndView showTaskForm( Either<DomainError, Task.Details> task ) {
        var model = new ModelAndView();
        var data = task.getData();

        if ( task.hasError() && Objects.isNull( data ) ) {
            return errorPage( HttpStatus.BAD_REQUEST );
        }

        if ( Objects.nonNull(data) ) {
            model.getModelMap().addAttribute( "id", task.getData().getId() );
            model.getModelMap().addAttribute( "label", task.getData().getLabel() );

        }

        if ( task.hasError() ) {
            //** anything mistakes in the model
        }

        model.setViewName( "task_form" );
        return model;
    }

    private ModelAndView errorPage( HttpStatus status ) {
        var redirectModel = new ModelAndView();
        redirectModel.setStatus( status );

        var errorTitle = "Error " + status.name() + " " + " Status: " + status.value();

        redirectModel.getModelMap().addAttribute( "errorTitle", errorTitle );

        redirectModel.setViewName( "error_page" );

        return redirectModel;
    }
}
