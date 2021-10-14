package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.model.Gender;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSubscriptionServiceImplTest {

    @InjectMocks
    private GetAllSubscriptionServiceImpl getAllSubscriptionService;

    @Mock
    private SubscriptionRepository repository;


    @Test
    public void giveListSubscriptions_whenGetAllSubscriptions_thenListSubscriptions() {
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

        given(repository.findAll())
                .willReturn(List.of(subscription));

        List<SubscriptionDTO> subscriptions = getAllSubscriptionService.getAll();

        assertNotNull(subscriptions);
        assertEquals(1, subscriptions.size());
        var subscriptionDTO = subscriptions.get(0);
        assertNotNull(subscription.getId());
        assertThat(subscriptionDTO.getEmail())
                .isEqualTo(subscription.getUser().getEmail());
        assertThat(subscriptionDTO.getNewsletterId())
                .isEqualTo(subscription.getNewsletter().getId());

        verify(repository, times(1)).findAll();


    }

    @Test
    public void giveNone_whenGetAllSubscriptions_thenReturnsNotFound() {
        given(repository.findAll()).willReturn(new ArrayList<>());

        try {
            List<SubscriptionDTO> subscriptions = getAllSubscriptionService.getAll();
            fail("There should have been an exception");

        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            verify(repository, times(1)).findAll();
        }

    }
}