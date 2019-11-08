package pl.coderslab.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.event.Event;

import java.util.List;

@Service
@Transactional
public class VenueService {

    private final VenueDao venueDao;
    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueDao venueDao, VenueRepository venueRepository) {
        this.venueDao = venueDao;
        this.venueRepository = venueRepository;
    }

    public List<Venue> getVenuesByTmp (byte tmp){
        return venueRepository.findByTmp(tmp);
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
