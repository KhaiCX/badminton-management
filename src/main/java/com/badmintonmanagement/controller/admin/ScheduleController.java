package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.service.CompetitionTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScheduleController {
    private final CompetitionTableService competitionTableService;
    public ScheduleController(CompetitionTableService competitionTableService) {
        this.competitionTableService = competitionTableService;
    }
    @GetMapping("/schedules")
    public String getAll(Model model) {
        List<CompetitionTable> competitionTableList = competitionTableService.getAll();
        model.addAttribute("competitionTables", competitionTableList);
        return "admin/schedule/schedules";
    }
}
