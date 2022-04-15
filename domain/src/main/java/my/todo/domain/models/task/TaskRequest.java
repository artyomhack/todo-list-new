package my.todo.domain.models.task;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Size;

public interface TaskRequest {

    @lombok.Data
    @AllArgsConstructor
    class Data implements TaskRequest {

        private Integer id;

        @Size(min = 5, max = 50)
        private String label;

    }

}
