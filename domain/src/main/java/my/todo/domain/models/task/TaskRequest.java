package my.todo.domain.models.task;

import lombok.AllArgsConstructor;

public interface TaskRequest {

    @lombok.Data
    @AllArgsConstructor
    class Data implements TaskRequest {
        private Integer id;
        private String label;
    }

}
