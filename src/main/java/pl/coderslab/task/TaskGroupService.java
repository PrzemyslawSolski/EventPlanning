package pl.coderslab.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskGroupService {

//    private final TaskGroupDao taskGroupDao;
    private final TaskGroupRepository taskGroupRepository;

    @Autowired
    public TaskGroupService(TaskGroupRepository taskGroupRepository) {
//        this.taskGroupDao = taskGroupDao;
        this.taskGroupRepository = taskGroupRepository;
    }

    public void create(TaskGroup taskGroup){
        taskGroupRepository.save(taskGroup);
    }

    public void update(TaskGroup taskGroup){
        taskGroupRepository.save(taskGroup);
    }

    public TaskGroup findOne(long id){
        return taskGroupRepository.findById(id).orElse(null);
    }

    public List<TaskGroup> findAll(){
        return taskGroupRepository.findAll();
    }

    public void delete(Long id){
//        taskGroupDao.deleteTaskGroupRelations(id);
        taskGroupRepository.deleteById(id);
    }
}
