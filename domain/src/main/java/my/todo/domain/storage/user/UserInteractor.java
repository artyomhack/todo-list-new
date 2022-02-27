package my.todo.domain.storage.user;

import my.todo.domain.models.user.User;
import my.todo.domain.common.DomainError;
import my.todo.domain.common.Either;
import my.todo.domain.models.user.UserRequest;

import java.util.List;
import java.util.Optional;

public class UserInteractor {

    private final UserStorage repository;

    public UserInteractor(UserStorage repository) {
        this.repository = repository;
    }

    public Either<DomainError, User.Details> create(UserRequest.Data request) {

        return repository.create(request)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.BadRequest(), null));
    }

    public Either<DomainError, User.Details> update(Integer id, UserRequest.Data request) {

        return repository.update(id, request)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound(), null));
    }

    public Either<DomainError, List<User.ListItem>> fetchAll() {

        return new Either<>(repository.fetchAll());
    }

    public Either<DomainError, User.Details> fetchById(Integer id) {

        return repository.fetchById(id)
                .map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound()));
    }

    public Either<DomainError, Boolean> removeById(Integer id) {

        return Optional.of(repository.removeById(id))
                .map(Either::new)
                .orElse(new Either<>(new DomainError.NotFound(), null));
    }
}
