package com.badmintonmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "athletes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer athleteId;
    private String athlete;
    private Integer numberOfWins;
    private Integer numberOfLosses;
    private Integer point;
    @ManyToOne
    @JoinColumn(name = "competition_table_id")
    private CompetitionTable competitionTable;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
