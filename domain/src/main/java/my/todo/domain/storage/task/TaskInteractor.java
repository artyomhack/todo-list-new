package my.todo.domain.storage.task;

import my.todo.domain.common.DomainError;
import my.todo.domain.common.Either;
import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;

import java.util.List;

public class TaskInteractor {

    private final TaskStorage repository;

    public TaskInteractor(TaskStorage repository) {
        this.repository = repository;
    }

    public Either<DomainError, Task.Details> create(TaskRequest.Data request) {

        return repository.create(request)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.BadRequest()));
    }

    public Either<DomainError, Task.Details> update(Integer id, TaskRequest.Data request) {

        return repository.update(id, request)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound()));
    }

    public Either<DomainError, List<Task.ListItem>> fetchAll() {

        return new Either<>(repository.fetchAll());
    }

    public Either<DomainError, Task.Details> fetchById(Integer id) {

        return repository.fetchById(id)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound()));
    }

    public Either<DomainError, Boolean> removeById(Integer id) {

        if (repository.removeById(id)) {
            return new Either<>(true);
        }
        return new Either<>(new DomainError.BadRequest());
    }
}
