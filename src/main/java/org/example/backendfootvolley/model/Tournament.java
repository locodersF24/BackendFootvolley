package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location; // Should this be an entity like "Address"?
    private String city;
    @ElementCollection
    private List<Integer> pointsAtStake;
    private LocalDate qualificationStartDate;
    private LocalDate qualificationEndDate;
    @Column(nullable = false)
    private LocalDate finalsStartDate;
    @Column(nullable = false)
    private LocalDate finalsEndDate;
    @ManyToMany
    private Set<Partner> partners;
    @ElementCollection
    private List<Integer> prizeMoney; // Lowest monetary unit (like cent for euro etc.)
    @Column(columnDefinition = "CHAR(3)", nullable = false)
    private String currency; // ISO 4217
    @ManyToOne
    @JoinColumn(nullable = false)
    private Club host;
    @ManyToOne
    @JoinColumn(nullable = false)
    private League league;
}
