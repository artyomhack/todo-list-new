package java.todo.domain.user;

import java.todo.domain.common.DomainError;
import java.todo.domain.common.Either;

public class UserInteractor {

    private final UserStore repository;

    public UserInteractor(UserStore repository) {
        this.repository = repository;
    }

    public Either<DomainError.BadRequest, User.Details> create(UserRequest.Data request) {
        var user = repository.create(request);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.BadRequest, User.Details> update(Integer id, UserRequest.Data request) {
        var user = repository.update(id, request);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }

    public Either<DomainError.BadRequest, Iterable<User.ListItem>> fetchList() {
        var users = repository.findAllUser();

        return new Either<>(null, users);
    }

    public Either<DomainError.BadRequest, User.Details> fetchUserById(Integer id) {
        var user = repository.findUserById(id);

        if (user == null) return new Either<>(new DomainError.BadRequest(), null);

        return new Either<>(null, user);
    }
}
