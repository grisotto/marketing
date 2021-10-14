package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.event.EventDispatcher;
import com.marketing.subscription.event.SubscriptionEvent;
import com.marketing.subscription.model.Gender;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.NewsletterRepository;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateSubscriptionServiceImplTest {

    @InjectMocks
    private CreateSubscriptionServiceImpl createSubscriptionService;

    @Mock
    private NewsletterRepository newsletterRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private EventDispatcher eventDispatcher;

    @Test
    public void giveSubscriptionDTO_whenCreateSubscription_thenReturnsSubscriptionId() {
        var dto = new SubscriptionDTO();
        dto.setConsent(true);
        dto.setEmail("email@email.com");
        dto.setDateOfBirth(LocalDate.now().minusDays(100));
        dto.setGender(Gender.MALE);
        dto.setFirstName("First name");
        dto.setNewsletterId(1L);

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
        var event = new SubscriptionEvent(subscription);

        given(userRepository.findById(anyString()))
                .willReturn(Optional.empty());

        given(userRepository.save(any()))
                .willReturn(user);

        given(newsletterRepository.findById(anyLong()))
                .willReturn(Optional.of(newsletter));

        given(subscriptionRepository.findSubscriptionByUserAndNewsletter(any(), any()))
                .willReturn(Optional.empty());

        given(subscriptionRepository.save(any()))
                .willReturn(subscription);

        Long subscriptionId = createSubscriptionService.create(dto);

        assertNotNull(subscriptionId);
        assertEquals(1L, subscriptionId);


        verify(userRepository).findById(any());
        verify(newsletterRepository).findById(anyLong());
        verify(eventDispatcher).send(eq(event));

    }

    @Test
    public void giveSubscriptionDTOButUserNotExist_whenCreateSubscription_thenReturnsSubscriptionId() {
        var dto = new SubscriptionDTO();
        dto.setConsent(true);
        dto.setEmail("email@email.com");
        dto.setDateOfBirth(LocalDate.now().minusDays(100));
        dto.setGender(Gender.MALE);
        dto.setFirstName("First name");
        dto.setNewsletterId(1L);

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
        var event = new SubscriptionEvent(subscription);

        given(userRepository.findById(anyString()))
                .willReturn(Optional.of(user));

        given(newsletterRepository.findById(anyLong()))
                .willReturn(Optional.of(newsletter));

        given(subscriptionRepository.findSubscriptionByUserAndNewsletter(any(), any()))
                .willReturn(Optional.empty());

        given(subscriptionRepository.save(any()))
                .willReturn(subscription);

        Long subscriptionId = createSubscriptionService.create(dto);

        assertNotNull(subscriptionId);
        assertEquals(1L, subscriptionId);


        verify(userRepository).findById(any());
        verify(newsletterRepository).findById(anyLong());
        verify(eventDispatcher).send(eq(event));

    }

    @Test
    public void giveSubscriptionDTOButSubscriptionExists_whenCreateSubscription_thenReturnsSubscriptionId() {
        var dto = new SubscriptionDTO();
        dto.setConsent(true);
        dto.setEmail("email@email.com");
        dto.setDateOfBirth(LocalDate.now().minusDays(100));
        dto.setGender(Gender.MALE);
        dto.setFirstName("First name");
        dto.setNewsletterId(1L);

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
        var event = new SubscriptionEvent(subscription);

        given(userRepository.findById(anyString()))
                .willReturn(Optional.of(user));

        given(newsletterRepository.findById(anyLong()))
                .willReturn(Optional.of(newsletter));

        given(subscriptionRepository.findSubscriptionByUserAndNewsletter(any(), any()))
                .willReturn(Optional.of(subscription));


        Long subscriptionId = createSubscriptionService.create(dto);

        assertNotNull(subscriptionId);
        assertEquals(1L, subscriptionId);


        verify(userRepository).findById(any());
        verify(newsletterRepository).findById(anyLong());
        verify(subscriptionRepository, times(0)).save(any());
        verify(eventDispatcher, times(0)).send(any());

    }

    @Test
    public void giveSubscriptionDTOButInvalidNewsletter_whenCreateSubscription_thenReturnsNotFound() {
        var dto = new SubscriptionDTO();
        dto.setConsent(true);
        dto.setEmail("email@email.com");
        dto.setDateOfBirth(LocalDate.now().minusDays(100));
        dto.setGender(Gender.MALE);
        dto.setFirstName("First name");
        dto.setNewsletterId(1L);

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
        var event = new SubscriptionEvent(subscription);

        given(userRepository.findById(anyString()))
                .willReturn(Optional.of(user));

        given(newsletterRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        try {
            Long subscriptionId = createSubscriptionService.create(dto);
            fail("There should have been an exception");

        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            verify(userRepository).findById(any());
            verify(newsletterRepository).findById(anyLong());
            verify(subscriptionRepository, times(0)).save(any());
            verify(eventDispatcher, times(0)).send(any());
        }


    }

}