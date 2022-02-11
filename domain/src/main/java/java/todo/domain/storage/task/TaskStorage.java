package java.todo.domain.storage.task;

import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;
import java.todo.domain.models.user.UserRequest;
import java.util.List;

public interface TaskStorage {

    Task.Details create(TaskRequest.Data request);

    Task.Details update(Integer id, TaskRequest.Data request);

    Iterable<Task.ListItem> fetchAll();

    Task.Details fetchById(Integer id);

    boolean removeById(Integer id);

}
