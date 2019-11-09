package pl.coderslab.login;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.user.LoginValidationGroup;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
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

        boolean loggedIn = true;
        User existingUser = userService.getFirstByEmail(user.getEmail());
        if (existingUser == null ||
                !BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
            loggedIn = false;
        }

        if (!loggedIn) {
            result.addError(new FieldError("user", "email",
                    "Niepoprawny email lub hasło"));
            return "login";
        }

        session.setAttribute("userId", existingUser.getId());
        session.setAttribute("firstName", existingUser.getName());

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
