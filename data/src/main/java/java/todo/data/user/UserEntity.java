package java.todo.data.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.todo.data.task.TaskEntity;
import java.todo.domain.user.User;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "usr")
public class UserEntity {
    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    @ManyToMany
    private List<TaskEntity> tasks = new ArrayList<>();


    public User.Details toDetails() {
        return new User.Details(
                id,
                firstName,
                lastName,
                middleName,
                tasks.stream().map(TaskEntity::toItem).toList()
        );
    }

    public User.ListItem toItem() {
        String fullName = firstName + " " + lastName + " " + middleName;

        return new User.ListItem(id, fullName);
    }
 }
