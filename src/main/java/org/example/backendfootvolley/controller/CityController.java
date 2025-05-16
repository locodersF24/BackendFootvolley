package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.City;
import org.example.backendfootvolley.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityRepository cityRepository;

    @GetMapping
    public ResponseEntity<SortedSet<City>> findCityByCountry(@RequestParam String country) {
        return ResponseEntity.ok(cityRepository.findAllByCountry(country));
    }

}
