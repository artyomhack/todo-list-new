package java.todo.data.task;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.todo.data.user.UserEntity;
import java.todo.domain.task.Task;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "task")
public class TaskEntity {
    @Id
    private Integer id;

    private String name;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();


    public Task.Details toDetails() {
        return new Task.Details(
                id,
                name,
                users.stream().map( UserEntity::toItem ).toList()
        );
    }

    public Task.ListItem toItem() {
        return new Task.ListItem( id, name );
    }
}
