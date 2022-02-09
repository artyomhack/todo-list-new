package java.todo.data.user;

import java.todo.domain.models.user.User;
import java.todo.domain.models.user.UserRequest;
import java.todo.domain.storage.user.UserStorage;

public class JpaUserStorage implements UserStorage {

    private UserRepository repository;

    public JpaUserStorage(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User.Details createUser(UserRequest request) {
        return null;
    }

    @Override
    public User.Details updateUser(Integer id, UserRequest.Data request) {
        return null;
    }

    @Override
    public Iterable<User.ListItem> fetchAllUser() {
        return null;
    }

    @Override
    public User.Details findUserById(Integer id) {
        return null;
    }

    @Override
    public User.Details getUser() {
        return null;
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return false;
    }

    @Override
    public User.Details editUser(UserRequest.Data request) {
        return null;
    }
}
