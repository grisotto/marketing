package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.model.Gender;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetSubscriptionServiceImplTest {

    @InjectMocks
    private GetSubscriptionServiceImpl getSubscriptionService;

    @Mock
    private SubscriptionRepository repository;


    @Test
    public void giveSubscriptionId_whenGetSubscription_thenSubscription() {
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

        SubscriptionDTO subscriptionDTO = getSubscriptionService.get(1L);

        assertNotNull(subscriptionDTO);
        assertNotNull(subscriptionDTO.getId());
        assertThat(subscriptionDTO.getEmail())
                .isEqualTo(subscription.getUser().getEmail());
        assertThat(subscriptionDTO.getNewsletterId())
                .isEqualTo(subscription.getNewsletter().getId());

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository).findById(idCaptor.capture());

        assertEquals(1L, idCaptor.getValue());

    }

    @Test
    public void giveInvalidSubscriptionId_whenGetSubscription_thenReturnExceptionNotFound() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        try {
            SubscriptionDTO subscriptionDTO = getSubscriptionService.get(1L);
            fail("There should have been an exception");

        } catch (Exception e) {
            assertNotNull(e);
            ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
            verify(repository).findById(idCaptor.capture());

            assertEquals(1L, idCaptor.getValue());
        }

    }

}