package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.client.CreateSubscriptionClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Random;
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
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(1000));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setFirstName("Rafael");
        subscriptionDTO.setNewsletterId(new Random().nextLong());

        try {
            String s = createSubscriptionService.create(subscriptionDTO);
            assertThat(s).isNotNull();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
