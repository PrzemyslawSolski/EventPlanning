package pl.coderslab.task;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TaskDao implements DaoInterface <Task> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Task task) {
        entityManager.persist(task);
    }

    @Override
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Override
    public Task findOne(long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findAll() {
        Query query = entityManager.createQuery("select t from Task t");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Task task = findOne(id);
        if (task != null){
            entityManager.remove(task);
        }
    }
}
