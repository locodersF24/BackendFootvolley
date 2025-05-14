package org.example.backendfootvolley.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewUserAccount {

    private String email;
    private String firstName;
    private String lastName;
    private String clubName;
    private String established;
    private String country;
    private String city;
    private String password;

    public boolean containsUnexpectedInput() {
        List<String> input = List.of(email, firstName, lastName, clubName, established, country, city, password);
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
