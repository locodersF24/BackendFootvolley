package org.example.backendfootvolley.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/club")
@PreAuthorize("hasAuthority('SCOPE_CLUB')")
public class ClubController {

    @GetMapping("/hello")
    public String dummy(Principal principal) {
        return "Hello " + principal.getName();
    }

}
