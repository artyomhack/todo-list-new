package java.todo.data.task;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.todo.data.user.UserEntity;
import java.todo.domain.models.task.Task;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "task")
public class TaskEntity {
    @Id
    private Integer id;

    private String label;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();


    public Task.Details toDetails() {
        return new Task.Details(
                id,
                label,
                users.stream().map( UserEntity::toItem ).toList()
        );
    }

    public Task.ListItem toItem() {
        return new Task.ListItem( id, label);
    }
}
