package org.example.backendfootvolley.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewClubUserAccount {

    private String password;
    private String clubName;
    private String established;
    private String email;
    private String firstName;
    private String lastName;
    // These
    private String country;
    private String cityName;
    // or this
    private Long cityId;

    public boolean containsUnexpectedInput() {
        List<String> input = new ArrayList<>(List.of(password,email, firstName, lastName, clubName, established));
        if (cityId == null) {
            input.add(country);
            input.add(cityName);
        }
        for (String s : input) {
            if (s == null) {
                return true;
            }
        }
        return input.contains("null") || input.contains("undefined");
    }

    public boolean hasValidEmail() {
        int i = email.lastIndexOf("@");
        int j = email.lastIndexOf(".");
        return i > 0 && j > 2 && i < j && email.length() - j > 2;
        // Checks that the string is of format c@c.cc
    }

}
