package pl.coderslab.eventTask;

import org.hibernate.mapping.ToOne;
import pl.coderslab.event.Event;
import pl.coderslab.price.Price;
import pl.coderslab.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "eventTasks")
public class EventTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Event event;
    private LocalDate date;
    private LocalTime time;
    private boolean completed;
    @OneToOne
    private Price price;

}
