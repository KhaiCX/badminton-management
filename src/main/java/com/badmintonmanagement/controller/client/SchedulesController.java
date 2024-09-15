package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.service.ScheduleService;
import com.badmintonmanagement.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class SchedulesController {
    private final ScheduleService scheduleService;
    private final TournamentService tournamentService;
    public SchedulesController(ScheduleService scheduleService, TournamentService tournamentService) {
        this.tournamentService = tournamentService;
        this.scheduleService = scheduleService;
    }
    @GetMapping
    public String schedules(Model model) {
        List<Tournament> tournaments = tournamentService.getAll();
        String tournamentIsNullOrEmpty = "";
        if (!tournaments.isEmpty()) {
            List<Schedule> schedules = scheduleService.getAll();
            model.addAttribute("schedules", schedules);
        } else {
            tournamentIsNullOrEmpty = "Chưa có giải đấu nào!!";
        }
        model.addAttribute("tournamentIsNullOrEmpty", tournamentIsNullOrEmpty);
        model.addAttribute("tournaments", tournaments);
        return "client/schedule/schedules";
    }
}
