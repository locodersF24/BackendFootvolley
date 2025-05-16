package org.example.backendfootvolley.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.example.backendfootvolley.model.City;
import org.example.backendfootvolley.model.NationalFederation;

@Getter
@Setter
public class ClubDTO {
    private Long id;
    private String name;
    private String established;
    private String logoBlobUrl;
    private NationalFederation nationalFederation;

}
