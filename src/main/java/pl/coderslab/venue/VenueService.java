package pl.coderslab.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.event.Event;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class VenueService {

//    private final VenueDao venueDao;
    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository) {
//        this.venueDao = venueDao;
        this.venueRepository = venueRepository;
    }

    public List<Venue> getUserVenuesQuery(String userId) {
        return venueRepository.getUserVenues(userId);
    }

    public Set<Venue> getUserPartyVenuesSet(long userId) {
        return venueRepository.getUserPartyVenuesSet(userId);
    }

    public Set<Venue> getUserCeremonyVenuesSet(long userId) {
        return venueRepository.getUserCeremonyVenuesSet(userId);
    }

    public Set<Venue> getUserVenues(long userId) {
        Set<Venue> venuesCeremony = getUserCeremonyVenuesSet(userId);
        Set<Venue> venuesParty = getUserPartyVenuesSet(userId);
        venuesCeremony.addAll(venuesParty);
        venuesCeremony.addAll(getVenuesByTmp((byte) 1));
        return venuesCeremony;
    }

    public void resetTmp(){
        Set<Venue> venueTmps = getVenuesByTmp((byte)1);
        for (Venue venueTmp : venueTmps) {
            venueTmp.setTmp((byte)0);
            venueRepository.save(venueTmp);
        }
    }


    public void save(Venue venue) {
        venueRepository.save(venue);
    }

    public Set<Venue> getVenuesByTmp(byte tmp) {
        return venueRepository.findByTmp(tmp);
    }

    public void create(Venue venue) {
        venueRepository.save(venue);
    }

    public void update(Venue venue) {
        venueRepository.save(venue);
    }

    public Venue findOne(long id) {
        return venueRepository.findById(id).orElse(null);
    }

    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    public void delete(Long id) {
//        venueDao.deleteVenueRelations(id);
        venueRepository.deleteById(id);
    }
}
