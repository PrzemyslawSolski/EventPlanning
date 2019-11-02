package pl.coderslab.task;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Task task) {
        entityManager.persist(task);
    }

    public void update(Task task) {
        entityManager.merge(task);
    }

    public Task findOne(long id) {
        return entityManager.find(Task.class, id);
    }

    public List<Task> findAll() {
        Query query = entityManager.createQuery("select t from Task t");
        return query.getResultList();
    }

    public void delete(Long id) {
        Task task = findOne(id);
        if (task != null){
            entityManager.remove(task);
        }
    }
}
