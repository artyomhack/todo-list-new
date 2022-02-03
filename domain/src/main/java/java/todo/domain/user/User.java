package java.todo.domain.user;

public interface User {

    class ListItem implements  User {
        private Integer id;
        private String fullName;
    }

    class Details implements User {
        private Integer id;
        private String firstName;
        private String lastName;
    }

}
