package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.CompetitionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionTableRepository extends JpaRepository<CompetitionTable, Integer> {
    @Query("select c from CompetitionTable c where c.tournament.tournamentId = :tournamentId")
    List<CompetitionTable> findCompetitionTablesByTournamentId(Integer tournamentId);
}
