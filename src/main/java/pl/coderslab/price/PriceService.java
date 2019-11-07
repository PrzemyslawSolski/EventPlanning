package pl.coderslab.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceService {

    private final PriceDao priceDao;
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceDao taskDao, PriceRepository priceRepository) {
        this.priceDao = taskDao;
        this.priceRepository = priceRepository;
    }

    public void save(Price price){
        priceRepository.save(price);
    }

    public void create(Price task) {
        priceDao.create(task);
    }

    public void update(Price task) {
        priceDao.update(task);
    }

    public Price findOne(long id) {
        return priceDao.findOne(id);
    }

    public List<Price> findAll() {
        return priceDao.findAll();
    }

    public void delete(Long id) {
//        priceDao.deletePriceRelations(id);
        priceDao.delete(id);
    }
}
