package pl.coderslab.event;

;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.eventTask.EventTask;
import pl.coderslab.eventTask.EventTaskService;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskGroupService;
import pl.coderslab.task.TaskService;
import pl.coderslab.taskToEvent.TaskToEvent;
import pl.coderslab.taskToEvent.TaskToEventListContainer;
import pl.coderslab.venue.Venue;
import pl.coderslab.venue.VenueService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;
    private final TaskService taskService;
    private final EventTaskService eventTaskService;
    private final TaskGroupService taskGroupService;

    @Autowired
    public EventController(EventService eventService, VenueService venueService, TaskService taskService, EventTaskService eventTaskService, TaskGroupService taskGroupService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.taskService = taskService;
        this.eventTaskService = eventTaskService;
        this.taskGroupService = taskGroupService;
    }

//TODO daty przy zapisywaniu są o jeden dzień wcześniejsze

    @GetMapping("/add")
    public String add(HttpSession session, Model model) {
        model.addAttribute("event", new Event());
//        List<Venue> venues = venueService.findAll();
//        model.addAttribute("venues", venues);
//        long userId = (Long) session.getAttribute("userId");
//        List<Venue> venues = venueService.getUserVenuesQuery(String.valueOf(userId));

        return "event";
    }

    @PostMapping("/add")
//    @ResponseBody
    public String add(HttpSession session,
                      @Valid @ModelAttribute Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "event";
        }
//        event.changeDates(1);
//TODO dodać event do listy eventów zalogowanego użytkownika
        eventService.saveEventWithVenues(session, event);

        session.setAttribute("eventId", event.getId());
//        event.changeDates(-1);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")//TODO zweryfikować sposób wyboru eventu do edycji, czy np. zmienna w sesji
    public String editEventById(Model model, @PathVariable long id) {
        model.addAttribute("event", eventService.findOne(id));
        return "event";
    }

    @PostMapping("/edit/{id}")
//    @ResponseBody
    public String editEventById(@ModelAttribute Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "event";
        }
//        event.changeDates(1);
        eventService.update(event);
//        event.changeDates(-1);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editEvent(HttpSession session, Model model) {

        try {
            model.addAttribute("event", eventService.findOne((Long) session.getAttribute("eventId")));
        } catch (NullPointerException e) {
            return "redirect:../add";
        }
        return "event";
    }

    @PostMapping("/edit")
//    @ResponseBody
    public String editEvent(HttpSession session, @Valid @ModelAttribute Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "event";
        }

        event.setId(eventService.findOne((Long) session.getAttribute("eventId")).getId());
        Event existingEvent = eventService.findOneWithUsers((Long) session.getAttribute("eventId"));
        event.setUsers(existingEvent.getUsers());
        eventService.saveEventWithVenues(session, event);
        return "redirect:/";
    }


    @GetMapping("/addTasks")
//    @ResponseBody
    public String addTasks(Model model, HttpSession session) {
        List<EventTask> eventTasks = eventTaskService.findAll();

        //stworzenie listy tasksId istniejących dla bieżącego eventu
        List<Long> eventTasksIds = new ArrayList<>();
        try {
            for (EventTask eventTask : eventTasks) {
                if (eventTask.getEvent().getId() == (Long) (session.getAttribute("eventId"))) { //TODO eventId
                    eventTasksIds.add(eventTask.getTask().getId());
                }
            }
        } catch (NullPointerException e) {
            return "redirect:./add";
        }
        List<Task> tasks = taskService.findAllEventNull();
//        List<Task> tasks = taskService.getByEventIdNullOrEventId((Long) (session.getAttribute("eventId")));
        List<TaskToEvent> taskToEvents = new ArrayList<>();
        for (Task task : tasks) {
            TaskToEvent taskToEvent = new TaskToEvent();
            taskToEvent.setTask(task);
            //jeżeli task jest na liście zadań bieżącego eventu, ustawiamy toAdd na true
//            if (eventTasksIds.contains(task.getId())) {
//                taskToEvent.setToAdd(true);
//            }
            // dodajemy do listy tylko te zadania, które nie są jeszcze wybrane do bieżącego wydarzenia
            if (!eventTasksIds.contains(task.getId())) {
                taskToEvents.add(taskToEvent);
            }
        }
        TaskToEventListContainer taskToEventList = new TaskToEventListContainer();
        taskToEventList.setTaskToEvents(taskToEvents);
        model.addAttribute("TaskToEvents", taskToEventList);
        model.addAttribute("taskGroups", taskGroupService.findAll());
        return "tasksToAdd";
    }

    @PostMapping("/addTasks")
    public String addTasks(HttpSession session, @ModelAttribute("TaskToEvents")
            TaskToEventListContainer taskToEventList,
                         BindingResult result) {
        List<TaskToEvent> taskToEvents = taskToEventList.getTaskToEvents();
        for (TaskToEvent taskToEvent : taskToEvents) {
//            taskToEvent.setTask(taskService.findOne(taskToEvent.getTask().getId()));
            if (taskToEvent.isToAdd()) {
                EventTask eventTask = new EventTask();
                eventTask.setTask(taskService.findOne(taskToEvent.getTask().getId()));
//                eventTask.setPrice(new Price());
                eventTask.setEvent(eventService.findOne((Long) (session.getAttribute("eventId"))));//TODO eventId
                eventTaskService.saveWithNewPrice(eventTask);
            }
        }
        return ("redirect:../tasks/list");
    }


    @ModelAttribute("venues")
    public Set<Venue> getVenue(HttpSession session) {
        return venueService.getUserVenues((Long) session.getAttribute("userId"));
    }
}
