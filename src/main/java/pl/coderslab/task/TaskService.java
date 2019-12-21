package pl.coderslab.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

//    private final TaskDao taskDao;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
//        this.taskDao = taskDao;
        this.taskRepository = taskRepository;
    }

    public List<Task> getByEventIdNullOrEventId(long eventId){
        return taskRepository.findByEventIdNullOrEventId(eventId);
    }

    public List<Task> findAllEventNull(){
//        return taskDao.findAllEventNull();
        return taskRepository.findByEventNull();
    }

    public void create(Task task){
        taskRepository.save(task);
    }

    public void update(Task task){
        taskRepository.save(task);
    }

    public Task findOne(long id){
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public void delete(Long id){
//        taskDao.deleteTaskRelations(id);
        taskRepository.deleteById(id);
    }
}
