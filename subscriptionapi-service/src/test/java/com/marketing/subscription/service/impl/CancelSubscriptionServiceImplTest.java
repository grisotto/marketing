package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.model.Gender;
import com.marketing.subscription.repository.SubscriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CancelSubscriptionServiceImplTest {

    @InjectMocks
    private CancelSubscriptionServiceImpl cancelSubscriptionService;

    @Mock
    private SubscriptionRepository repository;


    @Test
    public void giveSubscriptionId_whenCancelSubscription_then204() {
        User user = new User();
        user.setConsent(true);
        user.setEmail("email@email.com");
        user.setDateOfBirth(LocalDate.now().minusDays(100));
        user.setGender(Gender.MALE);
        user.setFirstName("First name");
        Newsletter newsletter = new Newsletter();
        newsletter.setId(1L);
        var subscription = new Subscription(user, newsletter);
        subscription.setId(1L);

        given(repository.findById(anyLong())).willReturn(Optional.of(subscription));


        cancelSubscriptionService.cancel(1L);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository).findById(idCaptor.capture());
        verify(repository).delete(subscription);

        assertEquals(1L, idCaptor.getValue());

    }

    @Test
    public void giveInvalidSubscriptionId_whenCancelSubscription_thenExceptionNoContent() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());


        try {
            cancelSubscriptionService.cancel(1L);
            fail("There should have been an exception");

        } catch (Exception e) {
            assertNotNull(e);
            ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
            verify(repository).findById(idCaptor.capture());
            verify(repository, times(0)).delete(any());


            assertEquals(1L, idCaptor.getValue());
        }


    }

}