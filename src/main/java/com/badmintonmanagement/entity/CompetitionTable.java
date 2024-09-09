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
}
