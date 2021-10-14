package com.marketing.mailer.event;

import com.marketing.mailer.model.Gender;
import com.marketing.mailer.service.api.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventHandlerTest {

    @InjectMocks
    private EventHandler eventHandler;

    @Mock
    private EmailService emailService;


    @Test
    public void giveEvent_whenMailerCreateSubscription_thenSendCreateSubscription() {
        var subscriptionEvent = new SubscriptionEvent();
        subscriptionEvent.setConsent(true);
        subscriptionEvent.setEmail("email@email.com");
        subscriptionEvent.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionEvent.setGender(Gender.MALE);
        subscriptionEvent.setFirstName("First name");
        subscriptionEvent.setNewsletterId(1L);
        subscriptionEvent.setSubscriptionId(1L);
        eventHandler.handleMailerCreateSubscription(subscriptionEvent);

        verify(emailService, times(1)).sendCreateSubscription(any());
//        verify(emailService).sendCreateSubscription();

    }

}