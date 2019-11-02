package pl.coderslab.price;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.task.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PriceDao implements DaoInterface <Price> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Price price) {
        entityManager.persist(price);
    }

    @Override
    public void update(Price price) {
        entityManager.merge(price);
    }

    @Override
    public Price findOne(long id) {
        return entityManager.find(Price.class, id);
    }

    @Override
    public List<Price> findAll() {
        Query query = entityManager.createQuery("select t from Price t");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Price price = findOne(id);
        if (price != null){
            entityManager.remove(price);
        }
    }
}
