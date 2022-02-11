package java.todo.data.task;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.todo.data.user.UserEntity;
import java.todo.domain.models.task.Task;
import java.todo.domain.models.task.TaskRequest;
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

    public TaskEntity() {

    }

    public TaskEntity(Integer id, String label) {

    }


    public static TaskEntity of(TaskRequest.Data request) {
        return new TaskEntity (
                request.getId(),
                request.getLabel()
        );
    }

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
