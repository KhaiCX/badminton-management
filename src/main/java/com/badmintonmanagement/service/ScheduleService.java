package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByTournamentAndCompetition(Tournament tournament, String competition) {
        return scheduleRepository.findAllByTournamentAndCompetition(tournament, competition);
    }

    public List<Schedule> getSchedulesByTournament(Tournament tournament) {
        return scheduleRepository.findAllByTournament(tournament);
    }

    public void savedSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule getById(Integer scheduleId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        return scheduleOptional.orElseGet(Schedule::new);
    }

    public void delete(Integer scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
