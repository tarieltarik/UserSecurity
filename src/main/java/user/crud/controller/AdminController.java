package user.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addUser");
        modelAndView.addObject("user", new User());
        List<Role> Setroles = roleService.getRolesList();
        modelAndView.addObject("rolelist", Setroles);
        return modelAndView;
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("authorities") String[] roles) {
        Set<Role> Setroles = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                Role role_admin = roleService.getRoleById(2L);
                Setroles.add(role_admin);
            }
            if (st.equals("USER")) {
                Role role_user = roleService.getRoleById(1L);
                Setroles.add(role_user);
            }
        }
        user.setRoles(Setroles);
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
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam("authorities") String[] roles){
        Set<Role> setroles = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                Role role_admin = roleService.getRoleById(2L);
                setroles.add(role_admin);
            }
            if (st.equals("USER")) {
                Role role_user = roleService.getRoleById(1L);
                setroles.add(role_user);
            }
        }
        user.setRoles(setroles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
