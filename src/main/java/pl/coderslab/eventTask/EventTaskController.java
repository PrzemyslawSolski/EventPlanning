package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.price.Price;

@Controller
@RequestMapping("/tasks")
public class EventTaskController {

    private final EventTaskService eventTaskService;

    @Autowired
    public EventTaskController(EventTaskService eventTaskService) {
        this.eventTaskService = eventTaskService;
    }

    @GetMapping("/edit/{id}")
    public String eventTaskEdit(Model model, @PathVariable long id) {
        EventTask eventTask=eventTaskService.getOneByIdAndEventId(id, 2);
        if(eventTask.getPrice()==null){
            eventTask.setPrice(new Price());
        }
        model.addAttribute("eventTask", eventTask);
        return "task";
    }
}
