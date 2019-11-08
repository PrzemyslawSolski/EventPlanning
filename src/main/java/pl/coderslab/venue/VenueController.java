package pl.coderslab.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventService;

import java.util.List;

@Controller
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("venue", new Venue());
        return "venue";
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(
            @ModelAttribute Venue venue, BindingResult result) {
        if (result.hasErrors()) {
            return "venue";
        }
        venueService.create(venue);
        return venue.toString();

    }

    @GetMapping("/addtmp")
    public String addtmp(Model model) {
        model.addAttribute("venue", new Venue());
        return "venue";
    }

    @PostMapping("/addtmp")
//    @ResponseBody
    public String addtmp(
            @ModelAttribute Venue venue, BindingResult result) {
        if (result.hasErrors()) {
            return "venue";
        }
        venue.setTmp((byte)1);
        venueService.create(venue);
        return "redirect:../events/add";

    }

}
