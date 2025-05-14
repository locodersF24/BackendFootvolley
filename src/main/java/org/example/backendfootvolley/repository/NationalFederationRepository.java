package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.NationalFederation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalFederationRepository extends JpaRepository<NationalFederation, Long> {
}

