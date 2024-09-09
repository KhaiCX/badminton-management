package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.Athlete;
import com.badmintonmanagement.entity.CompetitionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    @Query("select a from Athlete a where a.competitionTable = :competitionTable")
    List<Athlete> findAthletesByCompetitionTable(CompetitionTable competitionTable);
}
