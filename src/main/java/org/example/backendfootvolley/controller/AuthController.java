package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.UserAccount;
import org.example.backendfootvolley.dto.UserAccountDTO;
import org.example.backendfootvolley.repository.UserAccountRepository;
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
    private final UserAccountRepository userAccountRepository;

    @PostMapping("/token")
    public String token(@RequestBody UserAccountDTO userAccountDTO) throws AuthenticationException {
        System.out.println(userAccountDTO);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccountDTO.getEmail(), userAccountDTO.getPassword()));
        return tokenService.generateToken(authentication);
    }

    @GetMapping("/me")
    public UserAccountDTO me(Principal principal) {
        UserAccount userAccount = userAccountRepository.findByContact_Email(principal.getName()).get();
        return new UserAccountDTO(userAccount);
    }

}
