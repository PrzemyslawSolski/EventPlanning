package pl.coderslab.event;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.venue.Venue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EventDao implements DaoInterface <Event> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Event event) {
        entityManager.persist(event);
    }

    @Override
    public void update(Event event) {
        entityManager.merge(event);
    }

    @Override
    public Event findOne(long id) {
        return entityManager.find(Event.class, id);
    }

    @Override
    public List<Event> findAll() {
        Query query = entityManager.createQuery("select e from Event e");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Event event = findOne(id);
        if (event != null){
            entityManager.remove(event);
        }
    }
}
