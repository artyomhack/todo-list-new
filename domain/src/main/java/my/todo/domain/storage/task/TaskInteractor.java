package my.todo.domain.storage.task;

import my.todo.domain.common.DomainError;
import my.todo.domain.common.Either;
import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskInteractor {

    private final Validator validator;

    private final TaskStorage repository;

    public TaskInteractor(Validator validator, TaskStorage repository) {
        this.validator = validator;
        this.repository = repository;
    }


    public Either<DomainError, Task.Details> create(TaskRequest.Data request) {

        return repository.create(request)
                .map(task -> new Either<>(validateTask(request), task))
                .orElse(new Either<>(new DomainError.BadRequest()));
    }

    public Either<DomainError, Task.Details> update(Integer id, TaskRequest.Data request) {

        return repository.update(id, request)
                .map(task -> new Either<>(validateTask(request), task))
                .orElse(new Either<>(new DomainError.BadRequest()));
    }

    public Either<DomainError, List<Task.ListItem>> fetchAll() {

        return new Either<>(repository.fetchAll());
    }

    public Either<DomainError, Task.Details> fetchById(Integer id) {

        return repository.fetchById(id).map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound()));

    }

    public Either<DomainError, Boolean> removeById(Integer id) {

        if (repository.removeById(id)) {
            return new Either<>(true);
        }
        return new Either<>(new DomainError.BadRequest());
    }


    public DomainError validateTask(TaskRequest.Data request) {

        var validated = validator.validate(request);
        if (!validated.isEmpty()) {
            var errors = validated.stream()
                    .collect(Collectors.toMap(
                            it -> it.getPropertyPath().toString(),
                            ConstraintViolation::getMessage
                    ));
            return new DomainError.ValidationError(errors);
        }
        return null;
    }
}
