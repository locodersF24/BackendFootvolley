package org.example.backendfootvolley.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password_hash;
    private String email;
    private String role; //skal det være enum?

    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Club club;

}
