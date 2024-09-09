package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;
    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }
    public List<Athlete> getAthletesByCompetitionTable(CompetitionTable competitionTable) {
        return athleteRepository.findAthletesByCompetitionTable(competitionTable);
    }

    public void save(Athlete athlete) {
        athleteRepository.save(athlete);
    }
}
