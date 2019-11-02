package pl.coderslab.eventTask;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.task.TaskGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EventTaskDao implements DaoInterface <EventTask> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(EventTask taskGroup) {
        entityManager.persist(taskGroup);
    }

    @Override
    public void update(EventTask taskGroup) {
        entityManager.merge(taskGroup);
    }

    @Override
    public EventTask findOne(long id) {
        return entityManager.find(EventTask.class, id);
    }

    @Override
    public List<EventTask> findAll() {
        Query query = entityManager.createQuery("select et from EventTask et");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        EventTask eventTask = findOne(id);
        if (eventTask != null){
            entityManager.remove(eventTask);
        }
    }
}
