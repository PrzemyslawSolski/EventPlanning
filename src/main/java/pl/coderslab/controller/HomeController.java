package pl.coderslab.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskService;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final TaskService taskService;


    @Autowired
    public HomeController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


    @GetMapping("/")
//    @ResponseBody
    public String home(HttpSession session) {
        session.setAttribute("eventId", 2);//TODO wprowadzić eventId od usera
//        LocalDate date = LocalDate.now();
//        LocalTime time = new LocalTime();
//        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return "home";
    }


    @GetMapping("/venue")
    public String venue() {
        return "venue";
    }

    @GetMapping("/taskList")
    public String taskList(Model model) {
        List<Task> tasks = new ArrayList<>();
        tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
//    @ResponseBody
    public String register(@RequestParam String repeatPassword,
                           Model model, @Valid @ModelAttribute User user, BindingResult result) {
        if (!user.getPassword().equals(repeatPassword)) {
            result.rejectValue("password", "error.user", "Błędnie powtórzone hasło");
        }
        if (result.hasErrors()) {
            return "./register";
        }
//TODO        czy hasło nie powinno być od razu hashowane w setterze?
        user.setPasswordHash(user.getPassword());
//        Hibernate.initialize(user.getEvents());
        userService.create(user);
        return "./home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
