package com.badmintonmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompetitionTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competitionTableId;
    private String name;
    public CompetitionTable() {}
    public CompetitionTable(String name) {
        this.name = name;
    }

    public Integer getCompetitionTableId() {
        return competitionTableId;
    }

    public void setCompetitionTableId(Integer competitionTableId) {
        this.competitionTableId = competitionTableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
