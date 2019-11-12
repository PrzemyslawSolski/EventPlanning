package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.event.EventService;
import pl.coderslab.price.Price;
import pl.coderslab.price.PriceService;
import pl.coderslab.task.Task;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
        EventTask eventTask=eventTaskService.getOneByIdAndEventId(id, (Long)(session.getAttribute("eventId")));
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
        // przenieść do metody w serwisie
        priceService.save(eventTask.getPrice());
//        eventTask.setEvent(eventService.findOne((Long)session.getAttribute("eventId")));
        eventTaskService.save(eventTask);
        return eventTask.toString();
    }

    @GetMapping("/add")
    public String addTask(Model model){
        EventTask eventTask = new EventTask();
//        Task task = new Task();
//        eventTask.setTask(task);
//        Price price = new Price();
//        eventTask.setPrice(price);
        model.addAttribute("eventTask", eventTask);
        return "task";
    }

    @PostMapping("/add")
    public String addTask(HttpSession session, @ModelAttribute EventTask eventTask, BindingResult result){
        if(result.hasErrors()){
            return "task";
        }
        eventTaskService.saveNewTask(session, eventTask);
        return("redirect:schedule");
    }

    @GetMapping("/list")
    public String showTasks(Model model, HttpSession session) {
        if(session.getAttribute("eventId")!=null
                && !String.valueOf(session.getAttribute("eventId")).isEmpty()) {
            long eventId = (Long)session.getAttribute("eventId");
//        }
//        if (eventId != 0) {
            List<EventTask> eventTasks = eventTaskService.
                    getEventTasksByEventIdOrderByCompletedAscDateAsc(eventId);
//            List<EventTask> eventTasksEmptyDate = eventTasks
//                    .stream()
//                    .filter(et -> et.getDate()==null).collect(Collectors.toList());
//            eventTasks.removeAll(eventTasksEmptyDate);
//            eventTasks.addAll(eventTasksEmptyDate);
            model.addAttribute("eventTasks", eventTaskService.putEmptyDatesAtEnd(eventTasks));
        }
        return "schedule";
    }

    @GetMapping("/estimate")
    public String costEstimate(Model model, HttpSession session){
        if(session.getAttribute("eventId")!=null
                && !String.valueOf(session.getAttribute("eventId")).isEmpty()) {
            long eventId = (Long) session.getAttribute("eventId");
//            List<EventTask> eventTasks = eventTaskService.
//                    getEventTasksByEventId(eventId);
//            eventTasks = eventTasks.stream().filter(et-> et.getPrice().getAmount()>0).sorted().collect(Collectors.toList());
//            model.addAttribute("eventTasks", eventTasks);
            eventTaskService.calculateEstimate(session, eventId);
        }
        return "estimate";
    }




    @PostMapping("/list")
    public String showTasks(){
        return null;
    }
}
