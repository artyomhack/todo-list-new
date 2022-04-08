package my.todo.config;

import my.todo.data.task.JpaTaskStorage;
import my.todo.data.task.TaskRepository;
import my.todo.data.user.JpaUserStorage;
import my.todo.data.user.UserRepository;
import my.todo.domain.storage.task.TaskInteractor;
import my.todo.domain.storage.task.TaskStorage;
import my.todo.domain.storage.user.UserInteractor;
import my.todo.domain.storage.user.UserStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class AppConfig {

    @Bean
    Validator getHibernateValidator() {
        var factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Bean
    TaskInteractor getTaskInteractorAndValidator (Validator validator, TaskStorage storage) {
        return new TaskInteractor(validator, storage);
    }

    @Bean
    TaskStorage getTaskStorage(TaskRepository repository) {
        return new JpaTaskStorage(repository);
    }

    @Bean
    UserInteractor getInteractor(UserStorage storage) {
        return new UserInteractor(storage);
    }

    @Bean
    UserStorage getUserStorage(UserRepository userRepository, TaskRepository taskRepository) {
        return new JpaUserStorage(userRepository, taskRepository);
    }

}
