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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

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

    @PostMapping("/schedules/save")
    public String saveSchedules(Schedule schedule, RedirectAttributes ra) {
        User athlete1 = userService.getUserByName(schedule.getAthlete1());
        User athlete2 = userService.getUserByName(schedule.getAthlete2());
        schedule.setImageAthlete1(Objects.isNull(athlete1.getImage()) ? null : athlete1.getImage());
        schedule.setImageAthlete2(Objects.isNull(athlete1.getImage()) ? null : athlete1.getImage());
        Integer tournamentId = schedule.getTournament().getTournamentId();
        if (Objects.isNull(schedule.getResultRound3Athlete1())) {
            if (schedule.getResultRound1Athlete1() + schedule.getResultRound2Athlete1() > schedule.getResultRound1Athlete2() + schedule.getResultRound2Athlete2()) {
                schedule.setFinalResultAthlete1(2);
                schedule.setFinalResultAthlete2(0);
            }
            else {
                schedule.setFinalResultAthlete1(0);
                schedule.setFinalResultAthlete2(2);
            }
        }
        else {
            if (schedule.getResultRound1Athlete1() + schedule.getResultRound2Athlete1() + schedule.getResultRound3Athlete1() > schedule.getResultRound1Athlete2() + schedule.getResultRound2Athlete2() + schedule.getResultRound3Athlete2()) {
                schedule.setFinalResultAthlete1(2);
                schedule.setFinalResultAthlete2(1);
            }
            else {
                schedule.setFinalResultAthlete1(1);
                schedule.setFinalResultAthlete2(2);
            }
        }
        if (Objects.isNull(schedule.getScheduleId())) {
            ra.addFlashAttribute("success", "Insert schedule successfully!!!");
        }
        else {
            ra.addFlashAttribute("success", "Update schedule successfully!!!");
        }
        schedulesService.savedSchedule(schedule);
        return "redirect:/admin/tournaments/" + tournamentId + "/schedules";
    }
}
