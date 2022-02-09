package java.todo.data.task;

import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;
import java.todo.domain.storage.task.TaskStorage;
import java.todo.domain.models.user.UserRequest;
import java.util.stream.StreamSupport;

public class JpaTaskStorage implements TaskStorage {

    private TaskRepository repository;

    public JpaTaskStorage(TaskRepository repository) {
        this.repository = repository;
    }


    @Override
    public Task.Details createTask(TaskRequest.Data request) {
        return null;
    }

    @Override
    public Task.Details updateTask(Integer id, TaskRequest.Data request) {
        return null;
    }

    @Override
    public Iterable<Task.ListItem> fetchAllTask() {
        return null;
    }

    @Override
    public Task.Details findTaskById(Integer id) {
        return null;
    }

    @Override
    public Task.Details getTask() {
        return null;
    }

    @Override
    public boolean deleteTask() {
        return false;
    }

    @Override
    public boolean deleteTaskById(Integer id) {
        return false;
    }

    @Override
    public Task.Details editTask(TaskRequest.Data request) {
        return null;
    }
}
