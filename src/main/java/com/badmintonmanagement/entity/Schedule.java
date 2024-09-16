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
    private String user1;
    private String user2;
    private LocalDateTime time;
    private Integer resultRound1User1;
    private Integer resultRound2User1;
    private Integer resultRound3User1;
    private Integer resultRound1User2;
    private Integer resultRound2User2;
    private Integer resultRound3User2;
    private Integer finalResultUser1;
    private Integer finalResultUser2;
    private String category;
    private String imageUser1;
    private String imageUser2;
}
