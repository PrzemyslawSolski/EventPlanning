package pl.coderslab.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public User findOne(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        Query query = entityManager.createQuery("select u from User u");
        return query.getResultList();
    }

    public void delete(Long id) {
        User user = findOne(id);
        if (user != null){
            entityManager.remove(user);
        }
    }
}
