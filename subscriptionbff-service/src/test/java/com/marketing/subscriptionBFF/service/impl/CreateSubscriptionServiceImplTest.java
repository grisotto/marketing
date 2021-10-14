package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.client.CreateSubscriptionClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateSubscriptionServiceImplTest {

    @InjectMocks
    private CreateSubscriptionServiceImpl createSubscriptionService;

    @Mock
    private CreateSubscriptionClient client;

    @Test
    public void givenSubscriptionDTO_whenCreate_thenReturnsSubscriptionId() {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);

        when(client.create(any())).thenReturn(new ResponseEntity<>(1L, HttpStatus.OK));
        try {
            Long subscriptionId = createSubscriptionService.create(subscriptionDTO);
            assertEquals(1L, subscriptionId);

        } catch (Exception exception) {
            fail("There should have been no exception");
        }

    }

    @Test
    public void givenInvalidSubscriptionDTO_whenCreate_thenBadRequest() {
        when(client.create(any()))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "NewsLetterId not found"));
        try {
            Long subscriptionId = createSubscriptionService.create(new SubscriptionDTO());
            fail("There should have been an exception");

        } catch (Exception e) {
            assertNotNull(e);
        }

    }

}