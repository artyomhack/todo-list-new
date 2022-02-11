package java.todo.domain.models.user;

public interface UserRequest {

    class Data implements UserRequest {
        private Integer id;
        private String firstName;
        private String lastName;
        private String middleName;

        public Integer getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMiddleName() {
            return middleName;
        }
    }

}
