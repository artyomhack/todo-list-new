package java.todo.data.user;

import java.todo.data.task.TaskEntity;
import java.todo.domain.models.task.Task;
import java.todo.domain.models.user.User;
import java.todo.domain.models.user.UserRequest;
import java.todo.domain.storage.user.UserStorage;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JpaUserStorage implements UserStorage {

    private final UserRepository repository;

    public JpaUserStorage(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User.Details create(UserRequest.Data request) {

        return repository.save(UserEntity.of(request)).toDetails();
    }

    @Override
    public User.Details update(Integer id, UserRequest.Data request) {

        Optional<UserEntity> user = repository.findById(id);

        return user.get().toDetails();
    }

    @Override
    public Iterable<User.ListItem> fetchAll() {

        var user = repository.findAll();

        return StreamSupport.stream(user.spliterator(), false)
                .map(UserEntity::toItem).toList();
    }

    @Override
    public User.Details findById(Integer id) {

        return repository.findById(id).get().toDetails();
    }

    @Override
    public boolean deleteById(Integer id) {

        boolean isTaskExist = repository.existsById(id);

        if (isTaskExist) {

            repository.deleteById(id);

            return true;
        }

        return false;
    }
}
