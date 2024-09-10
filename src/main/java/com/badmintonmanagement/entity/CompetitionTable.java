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
}
