package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String location;
    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PointsAtStake pointsAtStake;
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
    @Column(columnDefinition = "CHAR(3)")
    private String currency; // ISO 4217
    @ManyToOne
    @JoinColumn(nullable = false)
    private Club host;
    @ManyToOne
    @JoinColumn(nullable = false)
    private League league;

    @JsonSetter("pointsAtStake")
    public void setPointsAtStakeFromJson(int pointsAtStake) {
        this.pointsAtStake = PointsAtStake.parse(pointsAtStake);
    }
}
