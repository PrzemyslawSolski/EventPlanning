package pl.coderslab.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findFirstByUsersId(long userId);

}
