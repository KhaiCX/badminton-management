package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.exception.CompetitionTableNotFoundException;
import com.badmintonmanagement.service.CompetitionTableService;
import com.badmintonmanagement.service.TournamentService;
import com.badmintonmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CompetitionTableController {
    private final CompetitionTableService competitionTableService;
    private final UserService userService;
    private final TournamentService tournamentService;
    public CompetitionTableController(TournamentService tournamentService, CompetitionTableService competitionTableService, UserService userService) {
        this.tournamentService = tournamentService;
        this.competitionTableService = competitionTableService;
        this.userService = userService;
    }
    @GetMapping("/tournaments/{tournamentId}/new")
    public String newCompetitionTableByTournament(@PathVariable Integer tournamentId, Model model) {
        CompetitionTable competitionTable = new CompetitionTable();
        competitionTable.setTournament(tournamentService.getById(tournamentId));
        model.addAttribute("competitionTable", competitionTable);
        model.addAttribute("title", "Add new Competition Table in the tournament");
        return "admin/competition/add_competitionTable";
    }
    @GetMapping("/tournaments/{tournamentId}/competitionTables/update/{competitionTableId}")
    public String updateCompetitionTableByTournament(@PathVariable Integer tournamentId,
                                                     @PathVariable Integer competitionTableId,
                                                     Model model) throws CompetitionTableNotFoundException {
        CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
        competitionTable.setTournament(tournamentService.getById(tournamentId));
        model.addAttribute("competitionTable", competitionTable);
        return "admin/competition/add_competitionTable";
    }
    @PostMapping("/competitionTables/save")
    public String savedCompetitionTable(CompetitionTable competitionTable, RedirectAttributes ra, Model model) {
        String success;
        if (competitionTable.getCompetitionTableId() != null) {
            success = "Update competition table: " + competitionTable.getName() + "successfully!!";
        }
        else {
            success = "Insert competition table: " + competitionTable.getName() + "successfully!!";
        }
        competitionTableService.save(competitionTable);
        ra.addFlashAttribute("success", success);
        model.addAttribute("message", "");
        return "redirect:/admin/tournaments/" + competitionTable.getTournament().getTournamentId() + "/competitionTables";
    }
    @GetMapping("/tournaments/{tournamentId}/competitionTables/delete/{competitionTableId}")
    public String deleteCompetitionTableByTournament(@PathVariable Integer tournamentId,
                                                     @PathVariable Integer competitionTableId,
                                                     RedirectAttributes ra) throws CompetitionTableNotFoundException {
        String name = competitionTableService.getCompetitionTableById(competitionTableId).getName();
        competitionTableService.delete(competitionTableId);
        ra.addFlashAttribute("success", "Delete Competition table: " + name + "successfully!!");
        return "redirect:/admin/tournaments/" + tournamentId + "/competitionTables";
    }
    @GetMapping("/competitionTables/{competitionTableId}")
    public String getAllAthletes(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException {
        try {
            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
            model.addAttribute("competitionTable", competitionTable);
            return "admin/competition/athletes";
        } catch (CompetitionTableNotFoundException ex) {
            throw new CompetitionTableNotFoundException(ex.getMessage());
        }
    }
    @GetMapping("/competitionTables/{competitionTableId}/athletes/new")
    public String newAthlete(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException {
        try {
            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
            List<User> users = userService.getAllUsers();
            Athlete athlete = new Athlete();
            athlete.setCompetitionTable(competitionTable);
            model.addAttribute("users", users);
            model.addAttribute("athlete", athlete);
            return "admin/competition/add_athlete";
        } catch (CompetitionTableNotFoundException ex) {
            throw new CompetitionTableNotFoundException(ex.getMessage());
        }
    }
}
