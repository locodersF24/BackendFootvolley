package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "CHAR(4)", nullable = false)
    private String seasonYear; // Variable name can't be "year"
    @Column(nullable = false)
    private Category category;

    @JsonSetter("category")
    public void setCategory(String category) {
        this.category = Category.parse(category);
    }
}
