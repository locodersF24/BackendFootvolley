package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Player player1;
    @ManyToOne
    private Player player2;
    @ManyToOne
    private Club club;

}
