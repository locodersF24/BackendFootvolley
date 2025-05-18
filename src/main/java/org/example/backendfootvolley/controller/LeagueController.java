package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    @GetMapping("/categories")
    public ResponseEntity<Category[]> getAllCategories() {
        return ResponseEntity.ok(Category.values());
    }

}
