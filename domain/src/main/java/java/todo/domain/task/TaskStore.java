package java.todo.domain.task;

public interface TaskStore {
    Task.Details create( UserRequest.Data request );

    Task.Details update( Integer id, UserRequest.Data request );

    Iterable<Task.ListItem> findAll();

    Task.Details findById( Integer id );

    boolean deleteById(Integer id);

}
