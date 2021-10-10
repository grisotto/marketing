package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.Subscription;
import com.marketing.subscriptionBFF.service.client.CreateSubscriptionClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionServiceImplTest {


    private CreateSubscriptionServiceImpl createSubscriptionService;

    @Mock
    private CreateSubscriptionClient createSubscriptionClient;

    @BeforeEach
    public void setup() {
        createSubscriptionService = new CreateSubscriptionServiceImpl(createSubscriptionClient);
    }


    @Test
    public void create() {
        Subscription subscription = new Subscription();
        subscription.setEmail("email@email.com");
        subscription.setDateOfBirth(LocalDate.now().minusDays(1000));
        subscription.setGender(Gender.MALE);
        subscription.setConsent(true);
        subscription.setFirstName("Rafael");
        subscription.setNewsletterId(UUID.randomUUID().toString());

        try {
            String s = createSubscriptionService.create(subscription);
            assertThat(s).isNotNull();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
