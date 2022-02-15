package my.todo.domain.storage.task;

import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskStorage {

    Optional<Task.Details> create(TaskRequest.Data request);

    Optional<Task.Details> update(Integer id, TaskRequest.Data request);

    List<Task.ListItem> fetchAll();

    Optional<Task.Details> fetchById(Integer id);

    boolean removeById(Integer id);

}
