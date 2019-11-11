package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskService;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final TaskService taskService;

    @Autowired
    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
//    @ResponseBody
    public String home(HttpSession session) {
        if(session.getAttribute("userId")==null || String.valueOf(session.getAttribute("userId")).isEmpty()) {
//            session.setAttribute("userId", 3l);//TODO usunąć
//            session.setAttribute("eventId", 2l);//TODO usunąć
        }
//        LocalDate date = LocalDate.now();
//        LocalTime time = new LocalTime();
//        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return "home";
    }

    @GetMapping("/venue")
    public String venue() {
        return "venue";
    }

    @GetMapping("/tasks")
    public String taskList(Model model) {
//        List<Task> tasks = new ArrayList<>();
//        tasks = taskService.findAll();
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

}
