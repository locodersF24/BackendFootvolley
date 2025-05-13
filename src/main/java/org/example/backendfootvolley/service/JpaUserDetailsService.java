package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.UserAccount;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository
                .findByContact_Email(username)
                .map(UserAccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @RequiredArgsConstructor
    public static class UserAccountDetails implements UserDetails {

        private final UserAccount userAccount;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(userAccount.getScope().toString()));
        }

        @Override
        public String getPassword() {
            return userAccount.getPassword();
        }

        @Override
        public String getUsername() {
            return userAccount.getContact().getEmail();
        }

        // The following are optional to implement, but since the default implementation is not relevant, they're all set to true.

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}
