package com.badmintonmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "competition_tables")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompetitionTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competitionTableId;
    private String name;
    private String athlete1;
    private String athlete2;
    private String athlete3;
    private String athlete4;
    private Integer numberOfWins1;
    private Integer numberOfWins2;
    private Integer numberOfWins3;
    private Integer numberOfWins4;
    private Integer numberOfLosses1;
    private Integer numberOfLosses2;
    private Integer numberOfLosses3;
    private Integer numberOfLosses4;
    private Integer pointOfAthlete1;
    private Integer pointOfAthlete2;
    private Integer pointOfAthlete3;
    private Integer pointOfAthlete4;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
