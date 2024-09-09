package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.exception.AthleteNotFoundException;
import com.badmintonmanagement.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;
    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }
    public List<Athlete> getAthletesByCompetitionTable(CompetitionTable competitionTable) {
        List<Athlete> athletes = athleteRepository.findAthletesByCompetitionTable(competitionTable);
        return athletes.stream().sorted(Comparator.comparing(Athlete:: getPoint).reversed()).collect(Collectors.toList());
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
