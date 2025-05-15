package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(columnDefinition = "CHAR(4)", nullable = false)
    private String established;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true, nullable = false)
    private City city;
    private String logoBlobUrl;
    @ManyToOne
    private NationalFederation nationalFederation;
    @JsonIgnore // Don't remove this
    @ManyToMany(mappedBy = "clubs")
    private Set<Player> players = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (object instanceof Club club) {
            return this.id.equals(club.id);
        }
        return false;
    }
}
