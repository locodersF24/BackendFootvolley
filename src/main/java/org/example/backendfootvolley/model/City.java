package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City implements Comparable<City> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private String country; // ISO 3166-1 alpha-2

    @Override
    public int compareTo(City city) {
        return name.compareTo(city.getName());
    }
}
