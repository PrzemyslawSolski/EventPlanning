package pl.coderslab.venue;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.task.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

//@Repository
//public class VenueDao {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void create(Venue venue) {
//        entityManager.persist(venue);
//    }
//
//    public void update(Venue venue) {
//        entityManager.merge(venue);
//    }
//
//    public Venue findOne(long id) {
//        return entityManager.find(Venue.class, id);
//    }
//
//    public List<Venue> findAll() {
//        Query query = entityManager.createQuery("select v from Venue v");
//        return query.getResultList();
//    }
//
//    public void delete(Long id) {
//        Venue venue = findOne(id);
//        if (venue != null){
//            entityManager.remove(venue);
//        }
//    }
//}
