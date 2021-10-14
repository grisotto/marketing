package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.service.client.CancelSubscriptionClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CancelSubscriptionServiceImplTest {

    @InjectMocks
    private CancelSubscriptionServiceImpl cancelSubscriptionService;

    @Mock
    private CancelSubscriptionClient client;


    @Test
    public void givenSubscriptionId_whenCancel_thenNoException() {
        try {
            cancelSubscriptionService.cancel(1L);

            ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
            verify(client).cancel(idCaptor.capture());

            assertEquals(1L, idCaptor.getValue());

        } catch (Exception exception) {
            fail("There should have been no exception");
        }

    }

    @Test
    public void givenInvalidSubscriptionId_whenCancel_thenNO_CONTENT() {
        when(client.cancel(anyLong()))
                .thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT, "Subscription not found"));
        try {
            cancelSubscriptionService.cancel(1L);
            fail("There should have been exception");

        } catch (Exception e) {
            assertNotNull(e);
            ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
            verify(client).cancel(idCaptor.capture());

            assertEquals(1L, idCaptor.getValue());
        }

    }

}