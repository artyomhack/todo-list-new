package java.todo.domain.storage.task;

import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;
import java.todo.domain.models.user.UserRequest;
import java.util.List;

public interface TaskStorage {

    Task.Details createTask(TaskRequest.Data request);

    Task.Details updateTask(Integer id, TaskRequest.Data request);

    Iterable<Task.ListItem> fetchAllTask();

    Task.Details findTaskById(Integer id);

    Task.Details getTask();

    boolean deleteTask();

    boolean deleteTaskById(Integer id);

    Task.Details editTask(TaskRequest.Data request);

}
