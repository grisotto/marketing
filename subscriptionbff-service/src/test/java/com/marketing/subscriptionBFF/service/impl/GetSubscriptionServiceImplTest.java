package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.client.GetSubscriptionClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetSubscriptionServiceImplTest {

    @InjectMocks
    private GetSubscriptionServiceImpl getAllSubscriptionService;

    @Mock
    private GetSubscriptionClient client;


    @Test
    public void givenSubscription_whenGet_thenSubscription() {

        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);

        when(client.get(anyLong()))
                .thenReturn(new ResponseEntity<>(subscriptionDTO, HttpStatus.OK));
        try {
            SubscriptionDTO subscriptionDTO1 = getAllSubscriptionService.get(1L);
            assertNotNull(subscriptionDTO1);
            assertThat(subscriptionDTO).isEqualTo(subscriptionDTO1);

        } catch (Exception exception) {
            fail("There should have been no exception");
        }

    }

    @Test
    public void givenNone_whenGetAll_thenNotFound() {

        when(client.get(anyLong()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        try {
            SubscriptionDTO subscriptionDTO = getAllSubscriptionService.get(1L);
            fail("There should have been an exception");

        } catch (Exception e) {
            assertNotNull(e);
        }

    }

}