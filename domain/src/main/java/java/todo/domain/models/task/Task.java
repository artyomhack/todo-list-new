package java.todo.domain.models.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.todo.domain.common.DomainData;
import java.todo.domain.models.user.User;
import java.util.List;

public interface Task extends DomainData {

    @Data
    @AllArgsConstructor
    class ListItem implements Task {
        private final Integer id;
        private final String label;
    }

    @Data
    @AllArgsConstructor
    class Details implements Task {
        private final Integer id;
        private final String label;
        private final List<User.ListItem> users;
    }

}
