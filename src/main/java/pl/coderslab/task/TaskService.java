package pl.coderslab.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskDao taskDao;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskDao taskDao, TaskRepository taskRepository) {
        this.taskDao = taskDao;
        this.taskRepository = taskRepository;
    }

    public List<Task> getByEventIdNullOrEventId(long eventId){
        return taskRepository.findByEventIdNullOrEventId(eventId);
    }

    public List<Task> findAllEventNull(){
        return taskDao.findAllEventNull();
    }

    public void create(Task task){
        taskDao.create(task);
    }

    public void update(Task task){
        taskDao.update(task);
    }

    public Task findOne(long id){
        return taskDao.findOne(id);
    }

    public List<Task> findAll(){
        return taskDao.findAll();
    }

    public void delete(Long id){
//        taskDao.deleteTaskRelations(id);
        taskDao.delete(id);
    }
}
