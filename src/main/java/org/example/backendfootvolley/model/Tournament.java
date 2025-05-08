package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;
    private String city;
    private int pointsAtStake;
    private LocalDate startDate;
    private LocalDate endDate;
    private String partners;
    private double prizeMoney;
    private String category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Club club; //host_club_id?
    @ManyToOne
    @JoinColumn(nullable = false)
    private League league;

}
