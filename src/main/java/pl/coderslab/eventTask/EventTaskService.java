package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.DaoInterface;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskDao;

import java.util.List;

@Service
@Transactional
public class EventTaskService {

    private final EventTaskDao eventTaskDao;

    @Autowired
    public EventTaskService(EventTaskDao eventTaskDao) {
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

    public List<EventTask> findAll(){
        return eventTaskDao.findAll();
    }

    public void delete(Long id){
//        eventTaskDao.deleteTaskRelations(id);
        eventTaskDao.delete(id);
    }
}
