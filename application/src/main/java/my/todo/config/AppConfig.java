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

@Configuration
public class AppConfig {

    @Bean
    TaskInteractor taskInteractor(TaskStorage storage) {
        return new TaskInteractor(storage);
    }

    @Bean
    TaskStorage taskStorage(TaskRepository repository) {
        return new JpaTaskStorage(repository);
    }

    @Bean
    UserInteractor getInteractor(UserStorage storage) {
        return new UserInteractor(storage);
    }

    @Bean
    UserStorage getUserStorage(UserRepository repository) {
        return new JpaUserStorage(repository);
    }

}
