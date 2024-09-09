package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.exception.AthleteNotFoundException;
import com.badmintonmanagement.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Athlete getAthletesById(Integer athleteId) throws AthleteNotFoundException {
        Optional<Athlete> athlete = athleteRepository.findById(athleteId);
        if (athlete.isPresent()) {
            return athlete.get();
        }
        else {
            throw new AthleteNotFoundException("Không tìm thấy vận động viên");
        }
    }
}
