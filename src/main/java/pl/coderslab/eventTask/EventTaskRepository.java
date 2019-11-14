package pl.coderslab.eventTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTaskRepository extends JpaRepository<EventTask, Long> {

    EventTask findByIdAndEventId(long taskEventId, long eventId);
    List<EventTask> findByEventIdOrderByDateAscCompletedAsc(long eventId);
    List<EventTask> findByEventId(long eventId);
}