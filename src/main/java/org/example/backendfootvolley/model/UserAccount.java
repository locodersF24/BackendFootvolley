package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true, nullable = false)
    private Contact contact;
    @JsonIgnore // For extra safety
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Club club;
}
