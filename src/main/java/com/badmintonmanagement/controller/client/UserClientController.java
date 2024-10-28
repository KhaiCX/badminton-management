package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.common.Const;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.service.DetailService;
import com.badmintonmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserClientController {
    private final UserService userService;
    private final DetailService detailService;
    @GetMapping()
    public String members(Model model) {
        List<User> users = userService.getAllUsers();
        String message = "";
        if (users.isEmpty()) {
            message = "Không có thành viên nào";
        }
        Map<String, List<Detail>> userDetailMap = new HashMap<>();
        for (User user : users) {
            List<Detail> details = detailService.getAllByUser(user, Const.TYPE_MEMBER);
            userDetailMap.put("detail" + user.getUserId(), details);
            model.addAttribute("userDetails", userDetailMap);
        }
        model.addAttribute("message", message);
        model.addAttribute("users", users);
        return "client/member/members";
    }
}
