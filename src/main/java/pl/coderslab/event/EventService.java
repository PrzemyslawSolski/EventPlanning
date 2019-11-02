package pl.coderslab.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventDao;

import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public void create(Event event){
        eventDao.create(event);
    }

    public void update(Event event){
        eventDao.update(event);
    }

    public Event findOne(long id){
        return eventDao.findOne(id);
    }

    public List<Event> findAll(){
        return eventDao.findAll();
    }

    public void delete(Long id){
//        eventDao.deleteEventRelations(id);
        eventDao.delete(id);
    }
}
