package java.todo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.todo.domain.task.Task;
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
        private String lastName;
        private String middleName;
        private List<Task.ListItem> tasks;
    }

}
