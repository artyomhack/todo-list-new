package java.todo.domain.models.task;

public interface TaskRequest {

    class Data implements TaskRequest {
        private Integer id;
        private String label;

        public Integer getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }
    }

}
