package user.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import user.crud.dao.RoleDAO;
import user.crud.model.Role;
import user.crud.model.User;
import user.crud.userService.RoleService;
import user.crud.userService.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @GetMapping(value = "/admin/add")
    public String addPage() {
        return "addUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminEditUser");
        modelAndView.addObject("user", user);
        List<Role> Setroles = roleService.getRolesList();
        modelAndView.addObject("rolelist", Setroles);
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(
            @ModelAttribute("user") User u
    ) {
        User user = userService.getById(u.getId());
        user.setName(u.getName());
        user.setLastname(u.getLastname());
        user.setAge(u.getAge());
        user.setCity(u.getCity());
        if (!u.getPassword().isEmpty()) {
            user.setPassword(u.getPassword());
        }
        user.setRoles(u.getRoles());
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
