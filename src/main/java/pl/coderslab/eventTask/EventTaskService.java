package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventTaskService {

    private final EventTaskRepository eventTaskRepository;

    public List<EventTask> getEventTasksByEventIdOrderByCompletedAscDateAsc(long eventId){
        return eventTaskRepository.findByEventIdOrderByCompletedAscDateAsc(eventId);
    }

    public EventTask getOne(long id) {
        return eventTaskRepository.getOne(id);
    }

    public EventTask getOneByIdAndEventId(long taskEventId, long eventId) {
        return eventTaskRepository.findByIdAndEventId(taskEventId, eventId);
    }

    public void save(EventTask eventTask){
        eventTaskRepository.save(eventTask);
    }

    public List<EventTask> findAll (){
        return eventTaskRepository.findAll();
    }

    public void delete(long id){
        eventTaskRepository.delete(getOne(id));
    }



    private final EventTaskDao eventTaskDao;

    @Autowired
    public EventTaskService(EventTaskRepository eventTaskRepository, EventTaskDao eventTaskDao) {
        this.eventTaskRepository = eventTaskRepository;
        this.eventTaskDao = eventTaskDao;
    }

    public void create(EventTask eventTask){
        eventTaskDao.create(eventTask);
    }

    public void update(EventTask eventTask){
        eventTaskDao.update(eventTask);
    }

    public EventTask findOne(long id){
        return eventTaskDao.findOne(id);
    }

//    public List<EventTask> findAll(){
//        return eventTaskDao.findAll();
//    }

//    public void delete(Long id){
////        eventTaskDao.deleteTaskRelations(id);
//        eventTaskDao.delete(id);
//    }
}
