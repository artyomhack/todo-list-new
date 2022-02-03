package java.todo.domain.task;

import java.todo.domain.common.DomainError;
import java.todo.domain.common.Either;

public class TaskInteractor {

    private final TaskStore repository;

    public TaskInteractor( TaskStore repository ) {
        this.repository = repository;
    }

    public Either<DomainError, Task.Details> create(UserRequest.Data request ) {
        var task = repository.create( request );

        if ( task == null ) {
            return new Either<>( new DomainError.BadRequest(), null );
        }

        return new Either<>( null, task );
    }

    public Either<DomainError, Task.Details> update( Integer id, UserRequest.Data request ) {
        var task = repository.update(id, request);

        if (task == null) {
            return new Either<>(new DomainError.BadRequest(), null);
        }

        return new Either<>(null, task);
    }

    public Either<DomainError, Iterable<Task.ListItem>> fetchList() {
        var tasks = repository.findAll();

        return new Either<>(null, tasks);
    }

    public Either<DomainError, Task.Details> fetchDetails(Integer id ) {
        var task = repository.findById(id);

        if (task == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, task);
    }

}
