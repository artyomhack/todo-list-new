package java.todo.domain.user;

public interface UserRequest {

    class Data implements UserRequest {
        private String firstName;
        private String lastName;
        private String middleName;

    }
}
