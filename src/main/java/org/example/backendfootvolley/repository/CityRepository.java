package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.SortedSet;

public interface CityRepository extends JpaRepository<City, Long> {
    SortedSet<City> findAllByCountry(String country);
}
