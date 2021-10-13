package com.marketing.subscription.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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

    public SubscriptionDTO(Subscription subscription) {
        User user = subscription.getUser();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
        this.consent = user.isConsent();
        this.newsletterId = subscription.getNewsletter().getId();
    }
}
