package pl.coderslab.eventTask;

import org.hibernate.mapping.ToOne;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.event.Event;
import pl.coderslab.price.Price;
import pl.coderslab.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "eventTasks")
public class EventTask implements Comparable<EventTask>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Event event;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;
    private boolean completed;
    @OneToOne
    private Price price;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EventTask{" +
                "id=" + id +
                ", task=" + task +
                ", event=" + event +
                ", date=" + date +
                ", time=" + time +
                ", completed=" + completed +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(EventTask task) {
        return this.getDate().compareTo(task.getDate());
    }
}
