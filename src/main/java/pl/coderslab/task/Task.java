package pl.coderslab.task;

import pl.coderslab.event.Event;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    @ManyToOne
    private Event event;
    //private long event_id; // – empty if general task, available for all; specific id if added by event’s admin
    @ManyToOne
    private TaskGroup taskGroup;
//    private long group_id;
}
