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

        public void setId(Integer id) {
            this.id = id;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getLastName() {
            return lastName;
        }
    }

}
