package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionServiceImplTest {


    private SubscriptionServiceImpl subscriptionService;

    @BeforeEach
    public void setup() {
        subscriptionService = new SubscriptionServiceImpl();
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
            String s = subscriptionService.create(subscription);
            assertThat(s).isNotNull();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void cancel() {
        try {
            boolean canceled = subscriptionService.cancel("1");
            assertThat(canceled).isTrue();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void get() {
        try {
            Subscription subscription = subscriptionService.get("1");
            assertThat(subscription).isInstanceOf(Subscription.class);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void getAll() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAll("1");
            assertThat(subscriptions).isInstanceOf(ArrayList.class);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
