package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DashboardController {
    private final UserService userService;
    @GetMapping
    public String dashboard(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("totalMember", users.size());
        return "admin/dashboard";
    }
}
