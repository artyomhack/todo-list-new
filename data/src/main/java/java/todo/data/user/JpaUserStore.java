package java.todo.data.user;

import java.todo.domain.user.User;
import java.todo.domain.user.UserRequest;
import java.todo.domain.user.UserStore;

public class JpaUserStore implements UserStore {

    private UserRepository repository;

    public JpaUserStore(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User.Details create(UserRequest.Data request) {
        return null;
    }

    @Override
    public User.Details update(Integer id, UserRequest.Data request) {
        return null;
    }

    @Override
    public Iterable<User.ListItem> findAllUser() {
        return null;
    }

    @Override
    public User.Details findUserById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return false;
    }
}
