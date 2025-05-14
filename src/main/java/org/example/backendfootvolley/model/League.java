package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "CHAR(4)", nullable = false)
    private String seasonYear; // Variable name can't be "year"
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
}
