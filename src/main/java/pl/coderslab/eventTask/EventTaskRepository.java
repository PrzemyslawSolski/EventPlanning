package pl.coderslab.eventTask;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTaskRepository extends JpaRepository<EventTask, Long> {

    EventTask findByIdAndEventId(long taskEventId, long eventId);
}
