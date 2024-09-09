package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.service.AthleteService;
import com.badmintonmanagement.service.CompetitionTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/schedules")
public class SchedulesController {
    private final CompetitionTableService competitionTableService;
    private final AthleteService athleteService;
    public SchedulesController(CompetitionTableService competitionTableService, AthleteService athleteService) {
        this.competitionTableService = competitionTableService;
        this.athleteService = athleteService;
    }
    @GetMapping
    public String schedules(Model model) {
        List<CompetitionTable> competitionTables = competitionTableService.getAll();
        CompetitionTable competitionTable1 = competitionTables.get(0);
        CompetitionTable competitionTable2 = competitionTables.get(1);
        List<Athlete> athletes1 = athleteService.getAthletesByCompetitionTable(competitionTable1);
        List<Athlete> athletes2 = athleteService.getAthletesByCompetitionTable(competitionTable2);
        String message1 = "";
        String message2 = "";
        if (athletes1.isEmpty()) {
            message1 = "Chưa có dữ liệu";
        }
        if (athletes2.isEmpty()) {
            message2 = "Chưa có dữ liệu";
        }
        model.addAttribute("competitionTable1", competitionTable1);
        model.addAttribute("competitionTable2", competitionTable2);
        model.addAttribute("athletes1", athletes1);
        model.addAttribute("athletes2", athletes2);
        model.addAttribute("message1", message1);
        model.addAttribute("message2", message2);
        return "client/schedule/schedules";
    }
}
