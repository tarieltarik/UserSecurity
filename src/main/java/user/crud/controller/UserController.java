package user.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import user.crud.model.User;
import user.crud.userService.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}