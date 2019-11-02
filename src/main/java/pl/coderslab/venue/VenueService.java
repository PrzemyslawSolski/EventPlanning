package pl.coderslab.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.DaoInterface;

import java.util.List;

@Service
@Transactional
public class VenueService {

    private final VenueDao venueDao;

    @Autowired
    public VenueService(VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    public void create(Venue venue) {
        venueDao.create(venue);
    }

    public void update(Venue venue) {
        venueDao.update(venue);
    }

    public Venue findOne(long id) {
        return venueDao.findOne(id);
    }

    public List<Venue> findAll() {
        return venueDao.findAll();
    }

    public void delete(Long id) {
//        venueDao.deleteVenueRelations(id);
        venueDao.delete(id);
    }
}
