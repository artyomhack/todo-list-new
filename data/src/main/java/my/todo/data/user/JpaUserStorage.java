package my.todo.data.user;

import my.todo.data.task.TaskEntity;
import my.todo.data.task.TaskRepository;
import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.models.user.User;
import my.todo.domain.models.user.UserRequest;
import my.todo.domain.storage.user.UserStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class JpaUserStorage implements UserStorage {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public JpaUserStorage(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<User.Details> create(UserRequest.Data request) {

        return Optional.of(userRepository.save(UserEntity.of(request)))
                .map(UserEntity::toDetails);
    }

    @Override
    public Optional<User.Details> update(Integer id, UserRequest.Data request) {

        return  userRepository.findById(id).map(it -> {
            var firstName = Optional.of(request.getFirstName()).orElse(it.getFirstName());

            var lastName = Optional.of(request.getLastName()).orElse(it.getLastName());

            var middleName = Optional.of(request.getMiddleName()).orElse(it.getMiddleName());

            var entity = new UserEntity(it.getId(), firstName, lastName, middleName);

            return userRepository.save(entity);
        }).map(UserEntity::toDetails);
    }

    @Override
    public List<User.ListItem> fetchAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserEntity::toItem).toList();
    }

    @Override
    public Optional<User.Details> fetchById(Integer id) {
        return userRepository.findById(id).map(UserEntity::toDetails);
    }

    @Override
    public boolean removeById(Integer id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<User.Details> addTaskToUser(Integer id,
                                                TaskRequest.Data taskRequest) {

        return userRepository.findById(id).map( user -> {
            var task = taskRepository.save(TaskEntity.of(taskRequest));
            user.addTask(task);
            return userRepository.save(user).toDetails();
        });

    }

    @Override
    public boolean removeTaskForUser(Integer userId, Integer taskId) {

        if (userRepository.existsById(userId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }

}
