package com.csc340.security_demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping({"/ADMIN", "/ADMIN/"})
    public String userMenu(@RequestParam(name = "continue", required = false) String cont) {
        return "user/menu";
    }

    @GetMapping("/ADMIN/user/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "continue",required = false) String cont) {
        model.addAttribute("userList", service.getAllUsers());
        return "user/user-list";
    }

    @GetMapping("/ADMIN/user/id={id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/user-details";
    }

    @GetMapping("/ADMIN/user/delete/id={id}")
    public String deleteUserLazy(@PathVariable long id, Model model) {
        service.deleteUser(id);
        return "redirect:/ADMIN/user/all";
    }

    @PostMapping("/ADMIN/user/create")
    public String createUser(User user) {

        service.saveUser(user);
        return "redirect:/ADMIN/user/all";
    }

    @PostMapping("/ADMIN/user/update")
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/ADMIN/user/all";
    }

    @GetMapping("/ADMIN/user/new-user")
    public String newUserForm(Model model) {
        return "user/user-new-form";
    }

    @GetMapping("/ADMIN/user/update/id={id}")
    public String updateUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/user-update-form";
    }

}
