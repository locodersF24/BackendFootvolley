package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lasName;
    private String city;
    private int points;
    private String playerPicture;

    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Club club;

}
