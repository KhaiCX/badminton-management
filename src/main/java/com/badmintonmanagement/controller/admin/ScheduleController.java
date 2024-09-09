package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.exception.CompetitionTableNotFoundException;
import com.badmintonmanagement.exception.UserNotFoundException;
import com.badmintonmanagement.service.AthleteService;
import com.badmintonmanagement.service.CompetitionTableService;
import com.badmintonmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScheduleController {
    private final CompetitionTableService competitionTableService;
    private final AthleteService athleteService;
    private final UserService userService;
    public ScheduleController(CompetitionTableService competitionTableService, AthleteService athleteService, UserService userService) {
        this.competitionTableService = competitionTableService;
        this.athleteService = athleteService;
        this.userService = userService;
    }
    @GetMapping("/schedules")
    public String getAllCompetitionTables(Model model) {
        List<CompetitionTable> competitionTableList = competitionTableService.getAll();
        model.addAttribute("competitionTables", competitionTableList);
        return "admin/schedule/schedules";
    }
    @GetMapping("/schedules/{competitionTableId}")
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
    @GetMapping("/schedules/{competitionTableId}/athlete/new")
    public String newAthlete(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException, UserNotFoundException {
        try {
            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
            List<User> users = userService.getAllUsers();
            User user = userService.getUserById(users.get(0).getUserId());
            Athlete athlete = new Athlete();
            athlete.setCompetitionTable(competitionTable);
            model.addAttribute("users", users);
            model.addAttribute("user", user);
            model.addAttribute("athlete", athlete);
            return "admin/schedule/add_athlete";
        } catch (CompetitionTableNotFoundException ex) {
            throw new CompetitionTableNotFoundException(ex.getMessage());
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }
    @PostMapping("/schedules/save")
    public String savedAthlete(Athlete athlete, RedirectAttributes ra) {
        athleteService.save(athlete);
        String success;
        if (athlete.getAthleteId() != null) {
            success = "Update athlete successfully!!";
        }
        else {
            success = "Insert athlete successfully!!";
        }
        ra.addFlashAttribute("success", success);
        return "redirect:" + athlete.getCompetitionTable().getCompetitionTableId();
    }
}
