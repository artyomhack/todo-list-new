package java.todo.domain.storage.user;

import java.todo.domain.common.DomainError;
import java.todo.domain.common.Either;
import java.todo.domain.models.user.User;
import java.todo.domain.models.user.UserRequest;

public class UserInteractor {

    private final UserStorage repository;

    public UserInteractor(UserStorage repository) {
        this.repository = repository;
    }

    public Either<DomainError.BadRequest, User.Details> create(UserRequest.Data request) {

        var user = repository.createUser(request);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.BadRequest, User.Details> update(Integer id, UserRequest.Data request) {

        var user = repository.updateUser(id, request);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.BadRequest, Iterable<User.ListItem>> fetchList() {

        var users = repository.fetchAllUser();

        return new Either<>(null, users);
    }

    public Either<DomainError.ValidationError, User.Details> fetchUserById(Integer id) {

        var user = repository.findUserById(id);

        if (user == null) return new Either<>(new DomainError.ValidationError(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.NotFound, User.Details> getUser() {

        var user = repository.getUser();

        if (user == null) return new Either<>(new DomainError.NotFound(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.ValidationError, Boolean> delete() {

        var isUserExist = repository.deleteUser();

        return new Either<>(null, isUserExist);
    }

    public Either<DomainError.ValidationError, Boolean> deleteUserById(Integer id) {

        var user = repository.deleteUserById(id);

        if (id == null) return new Either<>(new DomainError.ValidationError(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.BadRequest, User.Details> edit(UserRequest.Data request) {

        var user = repository.editUser(request);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }

}
