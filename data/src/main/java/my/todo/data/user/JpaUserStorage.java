package my.todo.data.user;

import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.models.user.User;
import my.todo.domain.models.user.UserRequest;
import my.todo.domain.storage.user.UserStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class JpaUserStorage implements UserStorage {

    private final UserRepository repository;

    public JpaUserStorage(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User.Details> create(UserRequest.Data request) {

        return Optional.of(repository.save(UserEntity.of(request)))
                .map(UserEntity::toDetails);
    }

    @Override
    public Optional<User.Details> update(Integer id, UserRequest.Data request) {

        return  repository.findById(id).map( it -> {
            var firstName = Optional.of(request.getFirstName()).orElse(it.getFirstName());

            var lastName = Optional.of(request.getLastName()).orElse(it.getLastName());

            var middleName = Optional.of(request.getMiddleName()).orElse(it.getMiddleName());

            var entity = new UserEntity(it.getId(), firstName, lastName, middleName);

            return repository.save(entity);
        }).map(UserEntity::toDetails);
    }

    @Override
    public List<User.ListItem> fetchAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(UserEntity::toItem).toList();
    }

    @Override
    public Optional<User.Details> fetchById(Integer id) {
        return repository.findById(id).map(UserEntity::toDetails);
    }

    @Override
    public boolean removeById(Integer id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<User.Details> addTaskToUser(UserRequest.Data userRequest,
                                                TaskRequest.Data taskRequest) {

        var user = repository.save(UserEntity.of(userRequest));
        user.addTaskToUser(taskRequest);

        return Optional.of(user.toDetails());
    }

}
