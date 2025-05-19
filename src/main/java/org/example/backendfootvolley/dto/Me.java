package org.example.backendfootvolley.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Me {
    private String email;
    private String role;
    private Long clubId;
}
