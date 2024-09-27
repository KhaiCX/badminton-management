package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.Schedule;
import com.badmintonmanagement.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("select s from Schedule s where s.tournament = :tournament and s.competition = :competition")
    List<Schedule> findAllByTournamentAndCompetition(Tournament tournament, String competition);
    @Query("select s from Schedule s where s.tournament = :tournament")
    List<Schedule> findAllByTournament(Tournament tournament);
}
