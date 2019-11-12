package pl.coderslab.task;

import pl.coderslab.event.Event;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String description;
    @ManyToOne
    private Event event;
    //private long event_id; // – empty if general task, available for all; specific id if added by event’s admin
    @ManyToOne
    private TaskGroup taskGroup;
//    private long group_id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", event=" + event +
                ", taskGroup=" + taskGroup +
                '}';
    }
}
