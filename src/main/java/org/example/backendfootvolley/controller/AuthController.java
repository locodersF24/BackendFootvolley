package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.UserProfile;
import org.example.backendfootvolley.model.UsernameAndPassword;
import org.example.backendfootvolley.model.UsernameAndRole;
import org.example.backendfootvolley.repository.UserProfileRepository;
import org.example.backendfootvolley.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserProfileRepository userProfileRepository;

    @PostMapping("/token")
    public String token(@RequestBody UsernameAndPassword usernameAndPassword) throws AuthenticationException, InterruptedException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword()));
        return tokenService.generateToken(authentication);
    }

    @GetMapping("/me")
    public UsernameAndRole me(Principal principal) {
        UserProfile userProfile = userProfileRepository.findByUsername(principal.getName()).get();
        return new UsernameAndRole(userProfile.getUsername(), userProfile.getScope().toString());
    }

}
