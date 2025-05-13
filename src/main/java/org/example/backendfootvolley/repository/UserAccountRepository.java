package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByContact_Email(String contactEmail);
    Optional<UserAccount> findByContact_Email(String contactEmail);
}
