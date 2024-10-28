package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.service.ScheduleService;
import com.badmintonmanagement.service.TournamentService;
import com.badmintonmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ScheduleAdminController {
    private final TournamentService tournamentService;
    private final ScheduleService schedulesService;
    private final UserService userService;
    @GetMapping("/tournaments/{tournamentId}/schedules/new")
    public String newSchedules(@PathVariable Integer tournamentId, Model model) {
        Tournament tournament = tournamentService.getById(tournamentId);
        Schedule schedule = new Schedule();
        schedule.setTournament(tournament);
        List<String> categories = List.of("Vòng loại", "Tứ kết", "Bán kết", "Chung kết");
        List<User> users = userService.getAllUsers();
        model.addAttribute("schedule", schedule);
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);
        model.addAttribute("title", "Add new Schedule of tournament: " + tournament.getName());
        return "admin/schedule/add_schedule";
    }

}
