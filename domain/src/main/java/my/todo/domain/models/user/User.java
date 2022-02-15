package my.todo.domain.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import my.todo.domain.models.task.Task;
import java.util.List;

public interface User {

    @Data
    @AllArgsConstructor
    class ListItem implements  User {
        private Integer id;
        private String fullName;

    }

    @Data
    @AllArgsConstructor
    class Details implements User {
        private Integer id;
        private String firstName;
        private String middleName;
        private String lastName;
        private List<Task.ListItem> tasks;
    }

}
