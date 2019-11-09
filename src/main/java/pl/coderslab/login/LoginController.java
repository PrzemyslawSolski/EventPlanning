package pl.coderslab.login;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventService;
import pl.coderslab.user.LoginValidationGroup;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;
    private final EventService eventService;

    public LoginController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated({LoginValidationGroup.class}) User user,
                        BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

//        boolean loggedIn = true;
        User existingUser = userService.getFirstByEmail(user.getEmail());
        if (existingUser == null ||
                !BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
//            loggedIn = false;
            result.addError(new FieldError("user", "email",
                    "Niepoprawny email lub hasło"));
            return "login";
        }

        session.setAttribute("userId", existingUser.getId());
        session.setAttribute("firstName", existingUser.getName());

        Event event = eventService.getFirstByUsersId(existingUser.getId());
        if (event != null) {
            session.setAttribute("eventId", event.getId());
        }else{
            session.removeAttribute("eventId");
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
