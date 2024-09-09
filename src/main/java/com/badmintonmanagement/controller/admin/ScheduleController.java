package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.exception.CompetitionTableNotFoundException;
import com.badmintonmanagement.service.AthleteService;
import com.badmintonmanagement.service.CompetitionTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ScheduleController {
    private final CompetitionTableService competitionTableService;
    private final AthleteService athleteService;
    public ScheduleController(CompetitionTableService competitionTableService, AthleteService athleteService) {
        this.competitionTableService = competitionTableService;
        this.athleteService = athleteService;
    }
    @GetMapping("/admin/schedules")
    public String getAllCompetitionTables(Model model) {
        List<CompetitionTable> competitionTableList = competitionTableService.getAll();
        model.addAttribute("competitionTables", competitionTableList);
        return "admin/schedule/schedules";
    }
    @GetMapping("/admin/schedules/{competitionTableId}")
    public String getAllAthletes(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException {
        try {
            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
            List<Athlete> athletes = athleteService.getAthletesByCompetitionTable(competitionTable);
            String message = "";
            if (athletes.isEmpty()) {
                message = "No information available";
            }
            model.addAttribute("message", message);
            model.addAttribute("competitionTable", competitionTable);
            model.addAttribute("athletes", athletes);
            return "admin/schedule/athletes";
        } catch (CompetitionTableNotFoundException ex) {
            throw new CompetitionTableNotFoundException(ex.getMessage());
        }
    }
}
