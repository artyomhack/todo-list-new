package my.todo.data.task;

import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.storage.task.TaskStorage;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class JpaTaskStorage implements TaskStorage {

    private final TaskRepository repository;

    public JpaTaskStorage(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task.Details> create(TaskRequest.Data request) {
        var entity = TaskEntity.of(request);
        return Optional.of(repository.save(entity).toDetails());
    }

    @Override
    public Optional<Task.Details> update(Integer id, TaskRequest.Data request) {
        return repository.findById(id).map( it -> {

            var label = Optional.of(request.getLabel()).orElse(it.getLabel());

            var entity = new TaskEntity(it.getId(), label);

            return repository.save(entity);
        }).map(TaskEntity::toDetails);
    }

    @Override
    public List<Task.ListItem> fetchAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(TaskEntity::toItem)
                .toList();
    }

    @Override
    public Optional<Task.Details> fetchById(Integer id) {
        return repository.findById(id).map(TaskEntity::toDetails);
    }

    @Override
    public boolean removeById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
