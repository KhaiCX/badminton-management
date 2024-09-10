package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }
}
