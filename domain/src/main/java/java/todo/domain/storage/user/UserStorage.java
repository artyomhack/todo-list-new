package java.todo.domain.storage.user;

import java.todo.domain.models.task.Task;
import java.todo.domain.models.user.User;
import java.todo.domain.models.user.UserRequest;


public interface UserStorage {

    User.Details createUser(UserRequest request);

    User.Details updateUser(Integer id, UserRequest.Data request);

    Iterable<User.ListItem> fetchAllUser();

    User.Details findUserById(Integer id);

    User.Details getUser();

    boolean deleteUser();

    boolean deleteUserById(Integer id);

    User.Details editUser(UserRequest.Data request);

}
