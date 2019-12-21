package pl.coderslab.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.event.id=null or t.event.id = ?1")
    public List<Task> findByEventIdNullOrEventId(long eventId );

    @Query("select t from Task t where t.event.id=null")
    public List<Task> findByEventNull();
}
