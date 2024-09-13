package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Tournament;
import com.badmintonmanagement.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    public void save(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public Tournament getById(Integer tournamentId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        return tournament.orElse(null);
    }

    public void delete(Integer tournamentId) {
        tournamentRepository.deleteById(tournamentId);
    }
}
