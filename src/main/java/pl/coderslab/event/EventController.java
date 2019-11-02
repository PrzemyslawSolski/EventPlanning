package pl.coderslab.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.venue.Venue;
import pl.coderslab.venue.VenueService;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;

    @Autowired
    public EventController(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;
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

    @ModelAttribute("venues")
    public List<Venue> getVenue() {
        return venueService.findAll();
    }
}
