//package org.example.backendfootvolley.dto;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.example.backendfootvolley.model.Club;
//import org.example.backendfootvolley.model.League;
//import org.example.backendfootvolley.model.Partner;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Set;
//
//public class CreateTournamentDTO {
//
//    @NotNull
//    public Long hostClubId;
//
//    @NotNull
//    public LocalDate qualificationStartDate;
//
//    @NotNull
//    public LocalDate qualificationEndDate;
//
//    @NotNull
//    public LocalDate finalsStartDate;
//
//    @NotNull
//    public LocalDate finalsEndDate;
//
//    @NotNull
//    @Size(min = 3, max = 3, message = "Prize money must include 3 values")
//    public Set<Integer> prizeMoney;
//
//    @NotNull
//    public Long leagueId;
//}



package org.example.backendfootvolley.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class CreateTournamentDTO {

    @NotNull
    public Long hostClubId;

    @NotNull
    public List<Integer> pointsAtStake;

    @NotNull
    public LocalDate qualificationStartDate;

    @NotNull
    public LocalDate qualificationEndDate;

    @NotNull
    public LocalDate finalsStartDate;

    @NotNull
    public LocalDate finalsEndDate;

    @NotNull
    @Size(min = 3, max = 3, message = "Prize money must include 3 values")
    public List<Integer> prizeMoney;

    @NotNull
    public Long leagueId;
}




