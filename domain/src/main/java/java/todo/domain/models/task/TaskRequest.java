package java.todo.domain.models.task;

public interface TaskRequest {

    class Data implements TaskRequest {
        private Integer id;
        private String label;
    }

}
