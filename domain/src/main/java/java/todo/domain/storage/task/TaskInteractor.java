package java.todo.domain.storage.task;

import java.todo.domain.common.DomainError;
import java.todo.domain.common.Either;
import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;

public class TaskInteractor {

    private final TaskStorage repository;

    public TaskInteractor(TaskStorage repository) {
        this.repository = repository;
    }

    public Either<DomainError.BadRequest, Task.Details> create(TaskRequest.Data request) {

        var task = repository.create(request);

        if (task == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, task);
    }

    public Either<DomainError.NotFound, Task.Details> update(Integer id, TaskRequest.Data request) {

        var task = repository.update(id, request);

        if (task == null) return new Either<>(new DomainError.NotFound(), null);

        return new Either<>(null, task);
    }

    public Iterable<Task.ListItem> fetchAllTask() {

        //I have to add returned error

        return repository.fetchAll();
    }

    public Either<DomainError.NotFound, Task.Details> findTaskById(Integer id) {

        var task = repository.fetchById(id);

        if (task == null) return new Either<>(new DomainError.NotFound(), null);

        return new Either<>(null, task);
    }

    public Either<DomainError.NotFound, Boolean> deleteTaskById(Integer id) {

        var isTaskExistById = repository.removeById(id);

        if (id == null) return new Either<>(new DomainError.NotFound(), null);

        return new Either<>(null, isTaskExistById);
    }
}
