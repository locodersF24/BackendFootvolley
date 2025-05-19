package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

    MEN("Men"),
    WOMEN("Women"),
    MEN_AND_WOMEN("Men/women");

    @JsonValue
    public final String value;

    public static Category parse(String value) {
        for (Category category : Category.values()) {
            if (category.value.equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
