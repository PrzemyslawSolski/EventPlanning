package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.event.EventService;
import pl.coderslab.price.Price;
import pl.coderslab.price.PriceService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class EventTaskController {

    private final EventTaskService eventTaskService;
    private final PriceService priceService;
    private final EventService eventService;

    @Autowired
    public EventTaskController(EventTaskService eventTaskService, PriceService priceService, EventService eventService) {
        this.eventTaskService = eventTaskService;
        this.priceService = priceService;
        this.eventService = eventService;
    }

    @GetMapping("/edit/{id}")
    public String eventTaskEdit(HttpSession session, Model model, @PathVariable long id) {
        EventTask eventTask=eventTaskService.getOneByIdAndEventId(id, (Integer)(session.getAttribute("eventId")));
        if(eventTask.getPrice()==null){
            eventTask.setPrice(new Price());
        }
        model.addAttribute("eventTask", eventTask);
        return "task";
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public String eventTaskEdit(HttpSession session, @ModelAttribute EventTask eventTask, BindingResult result){
        if(result.hasErrors()){
            return "task";
        }
        //TODO czy należy pobrać task i event przed zapisem eventTask?
        priceService.save(eventTask.getPrice());
//        eventTask.setEvent(eventService.findOne((Integer)session.getAttribute("eventId")));
        eventTaskService.save(eventTask);
        return eventTask.toString();
    }

    @GetMapping("/list")
    public String showTasks(Model model, HttpSession session) {
        if(session.getAttribute("eventId")!=null
                && !String.valueOf(session.getAttribute("eventId")).isEmpty()) {
            long eventId = (Integer) session.getAttribute("eventId");
//        }
//        if (eventId != 0) {
            List<EventTask> eventTasks = eventTaskService.
                    getEventTasksByEventIdOrderByCompletedAscDateAsc(eventId);
            model.addAttribute("eventTasks", eventTasks);
        }
        return "schedule";
    }

    @PostMapping("/list")
    public String showTasks(){
        return null;
    }
}
