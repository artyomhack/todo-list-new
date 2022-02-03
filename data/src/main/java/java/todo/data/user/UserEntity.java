package java.todo.data.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.todo.domain.user.User;

@Data
@Entity(name = "usr")
public class UserEntity {
    @Id
    private Integer id;


    public User.Details toDetails() {

    }

    public User.ListItem toItem() {

    }
 }
