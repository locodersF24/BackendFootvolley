package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player playerA;
    @ManyToOne
    private Player playerB;
    @ManyToOne
    private Club club;
}
