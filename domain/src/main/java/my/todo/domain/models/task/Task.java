package my.todo.domain.models.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import my.todo.domain.models.user.User;

import my.todo.domain.common.DomainEntity;

import java.util.List;

public interface Task extends DomainEntity {
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
