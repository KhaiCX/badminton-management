package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.exception.AthleteNotFoundException;
import com.badmintonmanagement.exception.CompetitionTableNotFoundException;
import com.badmintonmanagement.service.AthleteService;
import com.badmintonmanagement.service.CompetitionTableService;
import com.badmintonmanagement.service.TournamentService;
import com.badmintonmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class CompetitionTableController {
    private final CompetitionTableService competitionTableService;
    private final AthleteService athleteService;
    private final UserService userService;
    private final TournamentService tournamentService;
    public CompetitionTableController(TournamentService tournamentService, CompetitionTableService competitionTableService, AthleteService athleteService, UserService userService) {
        this.tournamentService = tournamentService;
        this.competitionTableService = competitionTableService;
        this.athleteService = athleteService;
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
            List<Athlete> athletes = athleteService.getAthletesByCompetitionTable(competitionTable);
            String message = "";
            if (athletes.isEmpty()) {
                message = "No information available";
            }
            model.addAttribute("message", message);
            model.addAttribute("competitionTable", competitionTable);
            model.addAttribute("athletes", athletes);
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
    @GetMapping("/tournaments/{tournamentId}/competitionTables/{competitionTableId}/athletes")
    public String getAthletesByCompetitionTable(@PathVariable Integer tournamentId,
                                                @PathVariable Integer competitionTableId,
                                                Model model) throws CompetitionTableNotFoundException {
        Tournament tournament = tournamentService.getById(tournamentId);
        CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
        List<Athlete> athletes = athleteService.getAthletesByCompetitionTable(competitionTable);
        String message = athletes.isEmpty() ? "No data available!!" : "";
        model.addAttribute("athletes", athletes);
        model.addAttribute("message", message);
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("competitionTableId", competitionTableId);
        model.addAttribute("title", "Manage Athlete in the Competition table: " + competitionTable.getName() + " of the tournament: " + tournament.getName());
        return "admin/athlete/athletes";
    }
    @GetMapping("/tournaments/{tournamentId}/competitionTables/{competitionTableId}/athletes/new")
    public String newAthlete(@PathVariable Integer tournamentId,
                             @PathVariable Integer competitionTableId,
                             Model model) throws CompetitionTableNotFoundException {
        Athlete athlete = new Athlete();
        Tournament tournament = tournamentService.getById(tournamentId);
        CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
        List<User> users = userService.getAllUsers();
        athlete.setCompetitionTable(competitionTable);
        model.addAttribute("athlete", athlete);
        model.addAttribute("users", users);
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("competitionTableId", competitionTableId);
        model.addAttribute("title", "Add athlete in the Competition table: " + competitionTable.getName() + " of the tournament:" + tournament.getName());
        return "admin/athlete/add_athlete";
    }
    @PostMapping("/athletes/save")
    public String savedAthlete(Athlete athlete,
                               @RequestParam Integer tournamentId,
                               RedirectAttributes ra) {
        athlete.setNumberOfWins(Objects.isNull(athlete.getNumberOfWins()) || Objects.equals(athlete.getNumberOfWins(), 0) ? 0 : athlete.getNumberOfWins());
        athlete.setNumberOfLosses(Objects.isNull(athlete.getNumberOfLosses()) || Objects.equals(athlete.getNumberOfLosses(), 0) ? 0 : athlete.getNumberOfLosses());
        athlete.setPoint(Objects.isNull(athlete.getPoint()) || Objects.equals(athlete.getPoint(), 0) ? 0 : athlete.getPoint());
        String success = athlete.getAthleteId() != null ? "Update athlete successfully!!" : "Insert athlete successfully!!";
        athleteService.save(athlete);
        ra.addFlashAttribute("success", success);
        return "redirect:/admin/tournaments/" + tournamentId + "/competitionTables/" + athlete.getCompetitionTable().getCompetitionTableId() + "/athletes";
    }
    @GetMapping("/competitionTables/{competitionTableId}/athletes/update/{athleteId}")
    public String updateAthlete(@PathVariable Integer competitionTableId,
                                @PathVariable Integer athleteId,
                                Model model) throws AthleteNotFoundException, CompetitionTableNotFoundException {
        Athlete athlete = athleteService.getAthletesById(athleteId);
        CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
        List<User> users = userService.getAllUsers();
        athlete.setCompetitionTable(competitionTable);
        model.addAttribute("athlete", athlete);
        model.addAttribute("tournamentId", competitionTable.getTournament().getTournamentId());
        model.addAttribute("competitionId", competitionTable.getCompetitionTableId());
        model.addAttribute("users", users);
        model.addAttribute("title", "Update athlete: " + athlete.getUser().getFullName());
        return "admin/athlete/add_athlete";
    }
    @GetMapping("/competitionTables/{competitionTableId}/athletes/delete/{athleteId}")
    public String deleteAthlete(@PathVariable Integer competitionTableId,
                                @PathVariable Integer athleteId,
                                RedirectAttributes ra,
                                Model model) throws AthleteNotFoundException, CompetitionTableNotFoundException {
        String success = "Delete athlete: " + athleteService.getAthletesById(athleteId).getUser().getFullName() + " successfully!!";
        athleteService.delete(athleteId);
        CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
        Tournament tournament = competitionTable.getTournament();
        ra.addFlashAttribute("success", success);
        model.addAttribute("title", "Manage Athlete in the Competition table: " + competitionTable.getName() + " of the tournament: " + tournament.getName());
        return "redirect:/admin/tournaments/" + tournament.getTournamentId() + "/competitionTables/" + competitionTableId + "/athletes";
    }
}
