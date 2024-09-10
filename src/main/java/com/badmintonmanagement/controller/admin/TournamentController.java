package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TournamentController {
    private final TournamentService tournamentService;
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
    @GetMapping("/tournaments")
    public String getAllTournaments(Model model) {
        List<Tournament> tournaments = tournamentService.getAll();
        String message = "";
        if (tournaments.isEmpty()) {
            message = "No data available!!";
        }
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("message", message);
        return "admin/tournament/tournaments";
    }
//    @GetMapping("/competitionTables/{competitionTableId}")
//    public String getAllAthletes(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException {
//        try {
//            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
//            List<Athlete> athletes = athleteService.getAthletesByCompetitionTable(competitionTable);
//            String message = "";
//            if (athletes.isEmpty()) {
//                message = "No information available";
//            }
//            model.addAttribute("message", message);
//            model.addAttribute("competitionTable", competitionTable);
//            model.addAttribute("athletes", athletes);
//            return "admin/competition/athletes";
//        } catch (CompetitionTableNotFoundException ex) {
//            throw new CompetitionTableNotFoundException(ex.getMessage());
//        }
//    }
//    @GetMapping("/competitionTables/{competitionTableId}/athletes/new")
//    public String newAthlete(@PathVariable Integer competitionTableId, Model model) throws CompetitionTableNotFoundException {
//        try {
//            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
//            List<User> users = userService.getAllUsers();
//            Athlete athlete = new Athlete();
//            athlete.setCompetitionTable(competitionTable);
//            model.addAttribute("users", users);
//            model.addAttribute("athlete", athlete);
//            return "admin/competition/add_athlete";
//        } catch (CompetitionTableNotFoundException ex) {
//            throw new CompetitionTableNotFoundException(ex.getMessage());
//        }
//    }
//    @GetMapping("/competitionTables/{competitionTableId}/athletes/{athleteId}")
//    public String updateAthlete(@PathVariable Integer competitionTableId, @PathVariable Integer athleteId, Model model) throws AthleteNotFoundException, CompetitionTableNotFoundException {
//        try {
//            CompetitionTable competitionTable = competitionTableService.getCompetitionTableById(competitionTableId);
//            List<User> users = userService.getAllUsers();
//            Athlete athlete = athleteService.getAthletesById(athleteId);
//            User user = athlete.getUser();
//            athlete.setCompetitionTable(competitionTable);
//            model.addAttribute("users", users);
//            model.addAttribute("user", user);
//            model.addAttribute("athlete", athlete);
//            return "admin/competition/add_athlete";
//        } catch (AthleteNotFoundException ex) {
//            throw new AthleteNotFoundException(ex.getMessage());
//        } catch (CompetitionTableNotFoundException ex) {
//            throw new CompetitionTableNotFoundException(ex.getMessage());
//        }
//    }
//    @PostMapping("/competitionTables/save")
//    public String savedAthlete(Athlete athlete, RedirectAttributes ra) {
//        athlete.setNumberOfWins(Objects.isNull(athlete.getNumberOfWins()) || Objects.equals(athlete.getNumberOfWins(), 0) ? 0 : athlete.getNumberOfWins());
//        athlete.setNumberOfLosses(Objects.isNull(athlete.getNumberOfLosses()) || Objects.equals(athlete.getNumberOfLosses(), 0) ? 0 : athlete.getNumberOfLosses());
//        athlete.setPoint(Objects.isNull(athlete.getPoint()) || Objects.equals(athlete.getPoint(), 0) ? 0 : athlete.getPoint());
//        //if (Objects.isNull(athlete.getNumberOfWins()) && Objects.equals(athlete.getNumberOfWins(), 0))
//        athleteService.save(athlete);
//        String success;
//        if (athlete.getAthleteId() != null) {
//            success = "Update athlete successfully!!";
//        }
//        else {
//            success = "Insert athlete successfully!!";
//        }
//        ra.addFlashAttribute("success", success);
//        return "redirect:" + athlete.getCompetitionTable().getCompetitionTableId();
//    }
}
