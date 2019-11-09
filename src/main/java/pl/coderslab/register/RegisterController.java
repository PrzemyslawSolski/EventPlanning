package pl.coderslab.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.user.RegistrationValidationGroup;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
//    @ResponseBody
    public String register(@RequestParam String repeatPassword,
                           Model model,
                           @Validated({RegistrationValidationGroup.class}) @ModelAttribute User user,
                           BindingResult result) {
        if (!user.getPassword().equals(repeatPassword)) {
            result.rejectValue("password", "error.user", "Błędnie powtórzone hasło");
        }
        if (result.hasErrors()) {
            return "register";
        }
        User existingUser = userService.getFirstByEmail(user.getEmail());
        if (existingUser != null) {
            result.addError(new FieldError("user", "email",
                    "Błędny email lub użytkownik istnieje"));
            return "register";
        }
        user.setPasswordHash(user.getPassword());
//        Hibernate.initialize(user.getEvents());
        userService.create(user);
        return "redirect:login";
    }


}
