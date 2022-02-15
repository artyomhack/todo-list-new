package my.todo.config;

import my.todo.data.task.JpaTaskStorage;
import my.todo.data.task.TaskRepository;
import my.todo.domain.storage.task.TaskInteractor;
import my.todo.domain.storage.task.TaskStorage;
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
}
