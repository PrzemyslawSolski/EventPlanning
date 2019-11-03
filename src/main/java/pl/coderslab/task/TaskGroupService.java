package pl.coderslab.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.DaoInterface;

import java.util.List;

@Service
@Transactional
public class TaskGroupService {

    private final TaskGroupDao taskGroupDao;

    @Autowired
    public TaskGroupService(TaskGroupDao taskGroupDao) {
        this.taskGroupDao = taskGroupDao;
    }

    public void create(TaskGroup taskGroup){
        taskGroupDao.create(taskGroup);
    }

    public void update(TaskGroup taskGroup){
        taskGroupDao.update(taskGroup);
    }

    public TaskGroup findOne(long id){
        return taskGroupDao.findOne(id);
    }

    public List<TaskGroup> findAll(){
        return taskGroupDao.findAll();
    }

    public void delete(Long id){
//        taskGroupDao.deleteTaskGroupRelations(id);
        taskGroupDao.delete(id);
    }
}
