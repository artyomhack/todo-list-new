package my.todo.domain.models.user;

import lombok.AllArgsConstructor;

public interface UserRequest {

    @lombok.Data
    @AllArgsConstructor
    class Data implements UserRequest {
        private Integer id;
        private String firstName;
        private String middleName;
        private String lastName;
    }

}
