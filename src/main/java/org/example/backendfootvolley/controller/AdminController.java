package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.Scope;
import org.example.backendfootvolley.model.UserProfile;
import org.example.backendfootvolley.model.UsernameAndPassword;
import org.example.backendfootvolley.repository.UserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public class AdminController {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UsernameAndPassword usernameAndPassword) {
        if (userProfileRepository.existsByUsername(usernameAndPassword.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(usernameAndPassword.getUsername());
        userProfile.setPassword("{bcrypt}" + passwordEncoder.encode(usernameAndPassword.getPassword()));
        userProfile.setScope(Scope.CLUB);
        userProfileRepository.save(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
