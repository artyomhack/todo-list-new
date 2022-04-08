package my.todo.domain.models.task;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface TaskRequest {

    @lombok.Data
    @AllArgsConstructor
    class Data implements TaskRequest {
        private Integer id;

        @Min(5)
        @Max(50)
        private String label;

    }

}
