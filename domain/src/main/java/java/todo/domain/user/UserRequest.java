package java.todo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

public interface UserRequest {

    class Data implements UserRequest {
        private Integer id;
        private String firstName;
        private String lastName;
        private String middleName;

    }
}
