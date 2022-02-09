package java.todo.domain.models.user;

public interface UserRequest {

    class Data implements UserRequest {
        private Integer id;
        private String firstName;
        private String lastName;
        private String middleName;

    }
}
