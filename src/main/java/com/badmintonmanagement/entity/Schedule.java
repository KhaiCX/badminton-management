package com.badmintonmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String athlete1;
    private String athlete2;
    private String pairOfAthletes1;
    private String pairOfAthletes2;
    private LocalDateTime time;
    private Integer resultRound1Athlete1;
    private Integer resultRound2Athlete1;
    private Integer resultRound3Athlete1;
    private Integer resultRound1Athlete2;
    private Integer resultRound2Athlete2;
    private Integer resultRound3Athlete2;
    private Integer finalResultAthlete1;
    private Integer finalResultAthlete2;
    private String category;
    private String imageAthlete1;
    private String imageAthlete2;
    private String competition;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
