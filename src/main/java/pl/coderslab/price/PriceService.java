package pl.coderslab.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskDao;

import java.util.List;

@Service
@Transactional
public class PriceService implements DaoInterface<Price> {

    private final PriceDao priceDao;

    @Autowired
    public PriceService(PriceDao taskDao) {
        this.priceDao = taskDao;
    }

    public void create(Price task){
        priceDao.create(task);
    }

    public void update(Price task){
        priceDao.update(task);
    }

    public Price findOne(long id){
        return priceDao.findOne(id);
    }

    public List<Price> findAll(){
        return priceDao.findAll();
    }

    public void delete(Long id){
//        priceDao.deletePriceRelations(id);
        priceDao.delete(id);
    }
}
