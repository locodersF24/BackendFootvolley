package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club>getClubById(Long clubId) {
        return clubRepository.findById(clubId);

    }

}
