package java.todo.data.task;

import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;
import java.todo.domain.storage.task.TaskStorage;
import java.todo.domain.models.user.UserRequest;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JpaTaskStorage implements TaskStorage {

    private final TaskRepository repository;

    public JpaTaskStorage(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task.Details create(TaskRequest.Data request) {
        var entity = TaskEntity.of(request);
        return repository.save(entity).toDetails();
    }

    @Override
    public Task.Details update(Integer id, TaskRequest.Data request) {


        return repository
                .findById(TaskEntity.of(request).getId())
                .get()
                .toDetails();
    }

    @Override
    public Iterable<Task.ListItem> fetchAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(TaskEntity::toItem)
                .toList();
    }

    @Override
    public Task.Details fetchById(Integer id) {

        Optional<TaskEntity> task = repository.findById(id);

        return task.map(TaskEntity::toDetails).orElse(null);
    }

    @Override
    public boolean removeById(Integer id) {

        var isTaskExist = repository.existsById(id);

        if (isTaskExist) {

            repository.deleteById(id);

            return  true;
        }

        return false;
    }
}
