package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/event")
    public String eventGeneral(){
        return "event";
    }

    @GetMapping("/venue")
    public String venue(){
        return "venue";
    }

    @GetMapping("/taskList")
    public String taskList(){
        return "taskList";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
