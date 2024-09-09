package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.CompetitionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionTableRepository extends JpaRepository<CompetitionTable, Integer> {
}
