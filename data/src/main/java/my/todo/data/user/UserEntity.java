package my.todo.data.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import my.todo.data.task.TaskEntity;
import my.todo.domain.models.user.User;
import my.todo.domain.models.user.UserRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "usr")
@AllArgsConstructor
public class UserEntity {
    @Id
    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_task_rel",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<TaskEntity> tasks = new ArrayList<>();

    public UserEntity() {}

    public UserEntity(Integer id, String firstName, String middleName, String lastName) {
        this(id, firstName, middleName, lastName, new ArrayList<>());
    }


    public User.Details toDetails() {
        return new User.Details(
                id,
                firstName,
                middleName,
                lastName,
                tasks.stream().map(TaskEntity::toItem).toList()
        );
    }

    public User.ListItem toItem() {
        String fullName = firstName + " " + middleName + " " + lastName;

        return new User.ListItem(id, fullName);
    }

    public static UserEntity of(UserRequest.Data request) {
        return new UserEntity(
                request.getId(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName()
        );
    }
 }
