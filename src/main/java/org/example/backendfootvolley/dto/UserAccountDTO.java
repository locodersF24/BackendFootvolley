package org.example.backendfootvolley.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendfootvolley.model.UserAccount;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password; // Request only
    private String role; // Response only

    public UserAccountDTO(UserAccount userAccount) {
        this.firstName = userAccount.getContact().getFirstName();
        this.lastName = userAccount.getContact().getLastName();
        this.email = userAccount.getContact().getEmail();
        this.role = userAccount.getScope().toString();
    }

    public boolean containsUnexpectedInput() {
        List<String> input = List.of(firstName, lastName, email, password);
        return input.contains(null) || input.contains("null") || input.contains("undefined");
    }

    public boolean hasInvalidEmail() {
        int i = email.lastIndexOf("@");
        int j = email.lastIndexOf(".");
        return i > 0 && j > 2 && i < j && email.length() - j > 2;
        // Checks that the string is of format c@c.cc
    }

}
