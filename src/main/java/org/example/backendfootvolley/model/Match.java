package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team teamA;
    @ManyToOne
    private Team teamB;
    @ManyToOne
    private Tournament tournament;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet1teamA;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet1teamB;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet2teamA;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet2teamB;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet3teamA;
    @Column(columnDefinition = "TINYINT")
    private Integer scoreSet3teamB;
}
