package pl.coderslab.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.user.User;
import pl.coderslab.user.UserDao;
import pl.coderslab.venue.Venue;
import pl.coderslab.venue.VenueService;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventDao eventDao;
    private final UserDao userDao;
    private final VenueService venueService;

    @Autowired
    public EventService(EventDao eventDao, UserDao userDao, VenueService venueService) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.venueService = venueService;
    }

    public void addUser(HttpSession session, Event event) {
        User user = userDao.findOne((Integer) session.getAttribute("userId"));
        event.getUsers().add(user);
    }

    public void saveEventWithVenues(HttpSession session, Event event) {
        Venue ceremonyVenue = venueService.findOne(event.getCeremonyVenue().getId());
        if (ceremonyVenue.getTmp() == 1) {
            ceremonyVenue.setTmp((byte) 0);
            venueService.update(ceremonyVenue);
        }
        event.setCeremonyVenue(ceremonyVenue);

        Venue partyVenue = venueService.findOne(event.getPartyVenue().getId());
        if (partyVenue.getTmp() == 1) {
            partyVenue.setTmp((byte) 0);
            venueService.update(partyVenue);
        }
        event.setPartyVenue(partyVenue);
        addUser(session, event);
        eventDao.create(event);
    }

    public void create(Event event) {
        eventDao.create(event);
    }

    public void update(Event event) {
        eventDao.update(event);
    }

    public Event findOne(long id) {
        return eventDao.findOne(id);
    }

    public List<Event> findAll() {
        return eventDao.findAll();
    }

    public void delete(Long id) {
//        eventDao.deleteEventRelations(id);
        eventDao.delete(id);
    }
}
