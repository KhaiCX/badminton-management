package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.service.ScheduleService;
import com.badmintonmanagement.service.TournamentService;
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
@RequestMapping("/schedules")
public class SchedulesController {
    private final ScheduleService scheduleService;
    private final TournamentService tournamentService;

    @GetMapping
    public String schedules(Model model) {
        List<Tournament> tournaments = tournamentService.getAll();
        String tournamentIsNullOrEmpty = "";
        Map<Tournament, List<Schedule>> tournamentSchedulesMap = new HashMap<>();
        if (!tournaments.isEmpty()) {
            for (Tournament tournament: tournaments) {
                List<Schedule> schedules = scheduleService.getSchedulesByTournament(tournament);
                tournamentSchedulesMap.put(tournament, schedules);
            }
        } else {
            tournamentIsNullOrEmpty = "Chưa có giải đấu nào!!";
        }
        model.addAttribute("tournamentIsNullOrEmpty", tournamentIsNullOrEmpty);
        model.addAttribute("tournamentSchedulesMap", tournamentSchedulesMap);
        return "client/schedule/schedules";
    }
}