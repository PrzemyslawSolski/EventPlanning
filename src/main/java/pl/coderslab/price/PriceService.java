package pl.coderslab.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceService {

//    private final PriceDao priceDao;
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService( PriceRepository priceRepository) {
//        this.priceDao = taskDao;
        this.priceRepository = priceRepository;
    }

    public void save(Price price){
        priceRepository.save(price);
    }

    public void create(Price task) {
        priceRepository.save(task);
    }

    public void update(Price task) {
        priceRepository.save(task);
    }

    public Price findOne(long id) {
        return priceRepository.findById(id).orElse(null);
    }

    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public void delete(Long id) {
//        priceDao.deletePriceRelations(id);
        priceRepository.deleteById(id);
    }
}
