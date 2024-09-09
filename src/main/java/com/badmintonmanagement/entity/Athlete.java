package com.badmintonmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer athleteId;
    private String athlete;
    private Integer numberOfWins;
    private Integer numberOfLosses;
    private Integer numberOfDraws;
    private Integer point;
    @ManyToOne
    @JoinColumn(name = "competition_table_id")
    private CompetitionTable competitionTable;
    public Athlete() {}

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthlete() {
        return athlete;
    }

    public void setAthlete(String athlete) {
        this.athlete = athlete;
    }

    public Integer getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(Integer numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public Integer getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses(Integer numberOfLosses) {
        this.numberOfLosses = numberOfLosses;
    }

    public Integer getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(Integer numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }
    public Integer getPoint() {
        return point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }
}
