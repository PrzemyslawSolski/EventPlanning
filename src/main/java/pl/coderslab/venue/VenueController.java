package pl.coderslab.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventService;
import pl.coderslab.user.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

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
//    @ResponseBody
    public String add(HttpSession session,
                      @ModelAttribute Venue venue, BindingResult result) {
        if (result.hasErrors()) {
            return "venue";
        }
        Long eventId = (Long) session.getAttribute("eventId");
        if (eventId == null || eventId == 0) {
            venue.setTmp((byte) 1);
        }
        venueService.save(venue);

        if (eventId == null || eventId == 0) {
            return "redirect:../events/add";
        } else {
            return "redirect:../events/edit";
        }
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
        venue.setTmp((byte) 1);
        venueService.create(venue);
        return "redirect:../events/add";
    }

    @GetMapping("/edit/{id}")
    public String editVenue(Model model, @PathVariable long id) {
        model.addAttribute("venue", venueService.findOne(id));
        return "venue";
    }

    @PostMapping("/edit/{id}")
    public String editVenue(@ModelAttribute Venue venue, BindingResult result) {
        if (result.hasErrors()) {
            return "venue";
        }
        venueService.save(venue);
        return "redirect:../list";
    }


    @GetMapping("/list")
    public String showVenues(HttpSession session, Model model) {
        Set<Venue> venues = venueService.getUserVenues((Long) session.getAttribute("userId"));
        session.setAttribute("venues", venues);
        return "venues";
    }

}
