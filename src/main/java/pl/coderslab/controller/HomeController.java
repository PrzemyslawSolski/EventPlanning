package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.task.TaskGroup;
import pl.coderslab.task.TaskGroupService;
import pl.coderslab.task.TaskService;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final TaskGroupService taskGroupService;

    @Autowired
    public HomeController(TaskService taskService, TaskGroupService taskGroupService) {
        this.taskService = taskService;
        this.taskGroupService = taskGroupService;
    }

    @GetMapping("/")
//    @ResponseBody
    public String home(HttpSession session) {
        if(session.getAttribute("userId")==null || String.valueOf(session.getAttribute("userId")).isEmpty()) {
//            session.setAttribute("userId", 3l);//TODO usunąć
//            session.setAttribute("eventId", 2l);//TODO usunąć
        }
        return "home";
    }

    @GetMapping("/venue")
    public String venue() {
        return "venue";
    }

    @GetMapping("/tasklist")
    public String taskList(Model model) {
//        List<Task> tasks = new ArrayList<>();
//        tasks = taskService.findAll();
        model.addAttribute("tasks", taskService.findAllEventNull());
        return "tasks";
    }

    @ModelAttribute("taskGroups")
    public List<TaskGroup> getTaskGroup(){
        return taskGroupService.findAll();
    }

}
