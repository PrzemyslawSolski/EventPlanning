package pl.coderslab.event;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.user.User;
import pl.coderslab.user.UserDao;
import pl.coderslab.venue.Venue;
import pl.coderslab.venue.VenueService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class EventService {

//    private final EventDao eventDao;
    private final EventRepository eventRepository;
    private final UserDao userDao;
    private final VenueService venueService;

    @Autowired
    public EventService( EventRepository eventRepository, UserDao userDao, VenueService venueService) {
//        this.eventDao = eventDao;
        this.eventRepository = eventRepository;
        this.userDao = userDao;
        this.venueService = venueService;
    }

    public void addUser(HttpSession session, Event event) {
        User user = userDao.findOne((Long) session.getAttribute("userId"));
        if(!event.getUsers().contains(user)) {
            event.getUsers().add(user);
        }
    }

    public Event getFirstByUsersId(long userId){
        return eventRepository.findFirstByUsersId(userId);
    }

    public void saveEventWithVenues(HttpSession session, Event event) {
//        Event existingEvent = eventDao.findOne(event.getId());
//        if(existingEvent!=null){
//            event.setId(existingEvent.getId());
//        }

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

//        venueService.resetTmp();
        addUser(session, event);
        eventRepository.save(event);
    }

    public Event findOneWithUsers(long id){
        Event event = findOne(id);
        Hibernate.initialize(event.getUsers());
        return event;
    }

    public void create(Event event) {
        eventRepository.save(event);
    }

    public void update(Event event) {
        eventRepository.save(event);
    }

    public Event findOne(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void delete(Long id) {
//        eventDao.deleteEventRelations(id);
        eventRepository.deleteById(id);
    }
}
