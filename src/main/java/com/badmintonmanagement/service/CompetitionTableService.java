package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.exception.CompetitionTableNotFoundException;
import com.badmintonmanagement.exception.DetailNotFoundException;
import com.badmintonmanagement.repository.CompetitionTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionTableService {
    private final CompetitionTableRepository competitionTableRepository;
    public CompetitionTableService(CompetitionTableRepository competitionTableRepository) {
        this.competitionTableRepository = competitionTableRepository;
    }
    public List<CompetitionTable> getAll() {
        return competitionTableRepository.findAll();
    }
    public CompetitionTable getCompetitionTableById(Integer competitionTableId) throws CompetitionTableNotFoundException {
        Optional<CompetitionTable> competitionTable = competitionTableRepository.findById(competitionTableId);
        if (competitionTable.isPresent()) {
            return competitionTable.get();
        }
        else {
            throw new CompetitionTableNotFoundException("Không tìm thấy bảng");
        }
    }

    public List<CompetitionTable> getCompetitionTableByTournamentId(Integer tournamentId) {
        return competitionTableRepository.findCompetitionTablesByTournamentId(tournamentId);
    }
}
