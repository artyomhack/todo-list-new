package java.todo.domain.task;

public interface TaskRequest {

    class Data implements TaskRequest {
        private Integer id;
        private String label;
    }

}
