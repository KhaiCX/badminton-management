package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedulesByTournament(Integer tournamentId) {
        return scheduleRepository.findAll();
    }
}
