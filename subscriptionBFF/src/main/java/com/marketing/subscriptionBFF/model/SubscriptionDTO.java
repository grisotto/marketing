package com.marketing.subscriptionBFF.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SubscriptionDTO {

    @ApiModelProperty(value = "Email which will receive the newsletter")
    @NotNull(message = "Email cannot be null")
    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("gender")
    private Gender gender;

    @NotNull(message = "Date of birth cannot be null")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @ApiModelProperty(value = "Consent about use of user data")
    @JsonProperty("consent")
    @AssertTrue(message = "Consent cannot be false")
    @NotNull
    private boolean consent;

    @Min(1)
    @NotNull(message = "NewsletterId cannot be null")
    @JsonProperty("newsletter_id")
    private long newsletterId;

}
