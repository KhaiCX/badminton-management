package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.service.ScheduleService;
import com.badmintonmanagement.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TournamentController {
    private final TournamentService tournamentService;
    private final ScheduleService scheduleService;
    public TournamentController(TournamentService tournamentService, ScheduleService scheduleService) {
        this.tournamentService = tournamentService;
        this.scheduleService = scheduleService;
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
    @GetMapping("/tournaments/new")
    public String newTournament(Model model) {
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("title", "Add New Tournament");
        return "admin/tournament/add_tournament";
    }
    @PostMapping("/tournaments/save")
    public String savedTournament(Tournament tournament, RedirectAttributes ra) {
        String success;
        if (tournament.getTournamentId() != null) {
            success = "Update tournament successfully!!";
        }
        else {
            success = "Insert tournament successfully!!";
        }
        tournamentService.save(tournament);
        ra.addFlashAttribute("success", success);
        return "redirect:/admin/tournaments";
    }
    @GetMapping("/tournaments/update/{tournamentId}")
    public String updateTournament(@PathVariable Integer tournamentId, Model model) {
        Tournament tournament = tournamentService.getById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("title", "Update Tournament: " + tournament.getName());
        return "admin/tournament/add_tournament";
    }
    @GetMapping("/tournaments/delete/{tournamentId}")
    public String deleteTournament(@PathVariable Integer tournamentId, RedirectAttributes ra) {
        String name = tournamentService.getById(tournamentId).getName();
        tournamentService.delete(tournamentId);
        ra.addFlashAttribute("success", "Delete Tournament: " + name + " successfully!!");
        return "redirect:/admin/tournaments";
    }
    @GetMapping("/tournaments/{tournamentId}/schedules")
    public String getSchedulesByTournament(@PathVariable Integer tournamentId,
                                           Model model) {
        Tournament tournament = tournamentService.getById(tournamentId);
        List<Schedule> schedules = scheduleService.getSchedulesByTournament(tournament);
        String message = schedules.isEmpty() ? "No data available" : "";
        model.addAttribute("message", message);
        model.addAttribute("schedules", schedules);
        model.addAttribute("tournamentId", tournamentId);
        return "admin/schedule/schedules";
    }
}
