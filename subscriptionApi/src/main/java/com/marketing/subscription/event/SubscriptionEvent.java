package com.marketing.subscription.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.model.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SubscriptionEvent {

    @JsonProperty("subscription_id")
    private Long subscriptionId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("consent")
    private boolean consent;

    @JsonProperty("newsletter_id")
    private long newsletterId;

    public SubscriptionEvent(Subscription subscription) {
        User user = subscription.getUser();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
        this.consent = user.isConsent();
        this.newsletterId = subscription.getNewsletter().getId();
        this.subscriptionId = subscription.getId();
    }
}
