package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.City;
import org.example.backendfootvolley.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_CLUB')")
    @PutMapping
    public ResponseEntity<Void> changeAttributesOfCity(@RequestBody City city) {
        if (!cityRepository.existsById(city.getId())) {
            return ResponseEntity.notFound().build();
        }
        cityRepository.save(city);
        return ResponseEntity.noContent().build();
    }

}
