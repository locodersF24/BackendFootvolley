package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String result;

    @ManyToOne
    private Team team1;
    @ManyToOne
    private Team team2;

    @ManyToOne
    private Tournament tournament;
}
