package com.marketing.subscriptionBFF.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Subscription {

    @NotNull
    @Email
    private String email;

    private String firstName;

    private Gender gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private boolean consent;

    @NotNull
    private String newsletterId;

}
