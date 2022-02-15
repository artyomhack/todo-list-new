package my.todo.presenter;

import my.todo.domain.storage.task.TaskInteractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskInteractor interactor;

    public TaskController(TaskInteractor interactor) {
        this.interactor = interactor;
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable String id) {
        var intId = Integer.parseInt(id);
        var task = interactor.fetchById(intId);
        return "";
    }
}
