package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NationalFederation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private String country; // ISO 3166-1 alpha-2
    private String logoBlobUrl;
}
