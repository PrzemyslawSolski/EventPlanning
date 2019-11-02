package pl.coderslab.task;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.price.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TaskGroupDao implements DaoInterface<TaskGroup> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(TaskGroup taskGroup) {
        entityManager.persist(taskGroup);
    }

    @Override
    public void update(TaskGroup taskGroup) {
        entityManager.merge(taskGroup);
    }

    @Override
    public TaskGroup findOne(long id) {
        return entityManager.find(TaskGroup.class, id);
    }

    @Override
    public List<TaskGroup> findAll() {
        Query query = entityManager.createQuery("select tg from TaskGroup tg");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        TaskGroup taskGroup = findOne(id);
        if (taskGroup != null) {
            entityManager.remove(taskGroup);
        }
    }
}
