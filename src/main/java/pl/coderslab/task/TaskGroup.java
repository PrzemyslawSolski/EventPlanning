package pl.coderslab.task;


import javax.persistence.*;

@Entity
@Table(name = "task_group")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
}
