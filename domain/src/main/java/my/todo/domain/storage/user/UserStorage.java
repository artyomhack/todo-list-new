package my.todo.domain.storage.user;

import my.todo.domain.models.task.TaskRequest;
import my.todo.domain.models.user.User;
import my.todo.domain.models.user.UserRequest;

import java.util.List;
import java.util.Optional;


public interface UserStorage {

    Optional<User.Details> create(UserRequest.Data request);

    Optional<User.Details> update(Integer id, UserRequest.Data request);

    List<User.ListItem> fetchAll();

    Optional<User.Details> fetchById(Integer id);

    boolean removeById(Integer id);

    Optional<User.Details> addTaskToUser(Integer userId,
                                         TaskRequest.Data taskRequest);

    boolean removeTaskForUser(Integer userId, Integer taskId);

}
