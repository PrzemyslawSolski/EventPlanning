package pl.coderslab.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.eventTask.EventTask;
import pl.coderslab.eventTask.EventTaskService;
import pl.coderslab.taskToEvent.TaskToEvent;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskService;
import pl.coderslab.taskToEvent.TaskToEventListContainer;
import pl.coderslab.venue.Venue;
import pl.coderslab.venue.VenueService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;
    private final TaskService taskService;
    private final EventTaskService eventTaskService;

    @Autowired
    public EventController(EventService eventService, VenueService venueService, TaskService taskService, EventTaskService eventTaskService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.taskService = taskService;
        this.eventTaskService = eventTaskService;
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("event", new Event());
//        List<Venue> venues = venueService.findAll();
//        model.addAttribute("venues", venues);
        return "event";
    }

    @PostMapping("/add")
//    @ResponseBody
    public String add(
            @ModelAttribute Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "event";
        }
        eventService.create(event);
        return "event";

    }

    @GetMapping("/addTasks")
//    @ResponseBody
    public String addTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        List<TaskToEvent> taskToEvents = new ArrayList<>();
        for (Task task : tasks) {
            TaskToEvent taskToEvent = new TaskToEvent();
            taskToEvent.setTask(task);
            taskToEvents.add(taskToEvent);
        }
        TaskToEventListContainer taskToEventList = new TaskToEventListContainer();
        taskToEventList.setTaskToEvents(taskToEvents);
        model.addAttribute("TaskToEvents", taskToEventList);

        return "tasksToAdd";
    }

    @PostMapping("/addTasks")
    public void addTasks(@ModelAttribute("TaskToEvents")
                                   TaskToEventListContainer taskToEventList,
                           BindingResult result) {
        List<TaskToEvent> taskToEvents = taskToEventList.getTaskToEvents();
        for (TaskToEvent taskToEvent : taskToEvents) {
//            taskToEvent.setTask(taskService.findOne(taskToEvent.getTask().getId()));
            if(taskToEvent.isToAdd()){
                EventTask eventTask = new EventTask();
                eventTask.setTask(taskService.findOne(taskToEvent.getTask().getId()));
                eventTask.setEvent(eventService.findOne(2));//TODO change event id to real one
                eventTaskService.create(eventTask);
            }
        }
    }


    @ModelAttribute("venues")
    public List<Venue> getVenue() {
        return venueService.findAll();
    }
}
