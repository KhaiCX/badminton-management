package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.exception.UserNotFoundException;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        String message = "";
        if (users.isEmpty()) {
            message = "Không có người dùng nào";
        }
        model.addAttribute("message", message);
        model.addAttribute("users", users);
        return "admin/user/users";
    }
    @GetMapping("/add-user")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/add_user";
    }
    @PostMapping("/add-user")
    public String saveUser(User user, RedirectAttributes ra) {
        userService.saveUser(user);
        ra.addFlashAttribute("message", "Add member successfully!");
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes ra) throws UserNotFoundException {
        User user = userService.getUserById(id);
        try {
            userService.deleteUser(user);
            ra.addFlashAttribute("message", "Delete member successfully");
        } catch (Exception ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
        return "redirect:/users";
    }
}
