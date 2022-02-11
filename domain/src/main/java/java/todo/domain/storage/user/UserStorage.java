package java.todo.domain.storage.user;

import java.todo.domain.models.user.User;
import java.todo.domain.models.user.UserRequest;


public interface UserStorage {

    User.Details create(UserRequest.Data request);

    User.Details update(Integer id, UserRequest.Data request);

    Iterable<User.ListItem> fetchAll();

    User.Details findById(Integer id);

    boolean deleteById(Integer id);

}
