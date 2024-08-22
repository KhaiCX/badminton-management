package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.common.Const;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.exception.DetailNotFoundException;
import com.badmintonmanagement.exception.UserNotFoundException;
import com.badmintonmanagement.service.DetailService;
import com.badmintonmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DetailController {
    private final DetailService detailService;
    private final UserService userService;
    public DetailController(DetailService detailService, UserService userService) {
        this.detailService = detailService;
        this.userService = userService;
    }
    @GetMapping("/detail/{userId}")
    public String getDetailsByUser(@PathVariable Integer userId, Model model) throws Exception {
        try {
            User user = userService.getUserById(userId);
            String message = "";
            List<Detail> details = detailService.getAllByUser(user, Const.TYPE_MEMBER);
            if (details.isEmpty()) {
                message = "Không có thông tin nào";
            }
            model.addAttribute("message", message);
            model.addAttribute("user", user);
            model.addAttribute("details", details);
            return "admin/detail/details";
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }
    @GetMapping("/detail/{userId}/update/{detailId}")
    public String getDetailsByUser(@PathVariable Integer userId,
                                   @PathVariable Long detailId,
                                   Model model) throws Exception {
        try {
            Detail detail = detailService.getDetailById(detailId);
            User user = userService.getUserById(userId);
            model.addAttribute("detail", detail);
            model.addAttribute("user", user);
            return "admin/detail/update_detail";
        } catch (DetailNotFoundException ex) {
            throw new DetailNotFoundException(ex.getMessage());
        }
    }
    @PostMapping("/update_detail")
    public String updateDetail(Detail detail, RedirectAttributes ra) {
        detail.setType(Const.TYPE_MEMBER);
        detailService.save(detail);
        String success = "Update detail member successfully!!";
        ra.addFlashAttribute("success", success);
        return "redirect:/admin/detail/" + detail.getUser().getUserId();
    }
}
