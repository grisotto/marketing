package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.client.GetAllSubscriptionClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSubscriptionServiceImplTest {

    @InjectMocks
    private GetAllSubscriptionServiceImpl getAllSubscriptionService;

    @Mock
    private GetAllSubscriptionClient client;


    @Test
    public void givenListSubscription_whenGetAll_thenListSubscription() {

        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        var subscriptions = List.of(subscriptionDTO);

        when(client.getAll())
                .thenReturn(new ResponseEntity<>(subscriptions, HttpStatus.OK));
        try {
            List<SubscriptionDTO> subscriptionDTOS = getAllSubscriptionService.getAll();
            assertNotNull(subscriptionDTOS);
            assertEquals(1L, subscriptionDTOS.size());
            assertThat(subscriptionDTO).isEqualTo(subscriptionDTOS.get(0));

        } catch (Exception exception) {
            fail("There should have been no exception");
        }

    }

    @Test
    public void givenNone_whenGetAll_thenNotFound() {

        when(client.getAll())
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        try {
            List<SubscriptionDTO> subscriptionDTOS = getAllSubscriptionService.getAll();
            fail("There should have been an exception");

        } catch (Exception e) {
            assertNotNull(e);
        }

    }

}