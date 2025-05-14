package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private City city;
    private String logoBlobUrl;
    @ManyToOne
    private NationalFederation nationalFederation;
}
