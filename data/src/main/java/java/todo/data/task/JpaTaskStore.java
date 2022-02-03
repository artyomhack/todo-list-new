package java.todo.data.task;

import java.todo.domain.task.Task;
import java.todo.domain.task.TaskStore;
import java.todo.domain.task.UserRequest;
import java.util.stream.StreamSupport;

public class JpaTaskStore implements TaskStore {

    private TaskRepository repository;

    public JpaTaskStore(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task.Details create( UserRequest.Data request ) {
        return null;
    }

    @Override
    public Task.Details update( Integer id, UserRequest.Data request ) {
        return null;
    }

    @Override
    public Iterable<Task.ListItem> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map( TaskEntity::toItem ).toList();
    }

    @Override
    public Task.Details findById( Integer id ) {
        return repository
                .findById( id )
                .map( TaskEntity::toDetails )
                .orElse( null );
    }

    @Override
    public boolean deleteById( Integer id ) {
        return false;
    }
}
