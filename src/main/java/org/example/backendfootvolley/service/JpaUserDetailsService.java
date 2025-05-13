package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.UserProfileDetails;
import org.example.backendfootvolley.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepository
                .findByUsername(username)
                .map(UserProfileDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
