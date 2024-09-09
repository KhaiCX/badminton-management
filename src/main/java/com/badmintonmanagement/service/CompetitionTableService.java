package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.CompetitionTable;
import com.badmintonmanagement.repository.CompetitionTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionTableService {
    private final CompetitionTableRepository competitionTableRepository;
    public CompetitionTableService(CompetitionTableRepository competitionTableRepository) {
        this.competitionTableRepository = competitionTableRepository;
    }
    public List<CompetitionTable> getAll() {
        return competitionTableRepository.findAll();
    }
}
