package java.todo.domain.user;

import java.todo.domain.task.UserRequest;

public interface UserStore {

    User.Details create(UserRequest.Data request);

    User.Details update(Integer id, UserRequest.Data request);

    Iterable<User.ListItem> findAllUser();

    User.Details findUserById(Integer id);

    boolean deleteUserById(Integer id);

}
