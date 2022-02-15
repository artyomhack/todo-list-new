package my.todo.data.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import my.todo.data.user.UserEntity;
import my.todo.domain.models.task.Task;
import my.todo.domain.models.task.TaskRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "task")
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String label;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_task_rel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<UserEntity> users;

    public TaskEntity(Integer id, String label) {
        this(id, label, new ArrayList<>());
    }

    public TaskEntity() {}

    public static TaskEntity of(TaskRequest.Data request) {
        return new TaskEntity(
                request.getId(),
                request.getLabel(),
                new ArrayList<>()
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
