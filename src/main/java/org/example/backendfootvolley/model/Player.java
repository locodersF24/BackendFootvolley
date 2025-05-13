package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private Contact contact;
    private String nickName;
    private String portraitBlobUrl;
    @ManyToMany
    private Set<Club> clubs;
}
