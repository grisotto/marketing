package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.event.EventDispatcher;
import com.marketing.subscription.event.SubscriptionEvent;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.NewsletterRepository;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.repository.UserRepository;
import com.marketing.subscription.service.api.CreateSubscriptionService;
import com.marketing.subscription.utils.MessageProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Log4j2
@Service
public class CreateSubscriptionServiceImpl implements CreateSubscriptionService {

    private final NewsletterRepository newsletterRepository;

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final EventDispatcher eventDispatcher;

    public CreateSubscriptionServiceImpl(NewsletterRepository newsletterRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository, EventDispatcher eventDispatcher) {
        this.newsletterRepository = newsletterRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventDispatcher = eventDispatcher;
    }


    @Override
    public Long create(SubscriptionDTO subscriptionDTO) {
        Optional<User> userByEmail = userRepository.findById(subscriptionDTO.getEmail());

        User user = userByEmail.orElseGet(() -> {
            log.info("User: {} doesnt exist.", subscriptionDTO.getEmail());
            var userSaved = new User(subscriptionDTO.getEmail(), subscriptionDTO.getFirstName(), subscriptionDTO.getGender(), subscriptionDTO.getDateOfBirth(), subscriptionDTO.isConsent());
            userRepository.save(userSaved);
            log.info("User: {} created.", subscriptionDTO.getEmail());
            return userSaved;
        });

        Optional<Newsletter> newsletterOptional = newsletterRepository.findById(subscriptionDTO.getNewsletterId());
        Newsletter newsletter = newsletterOptional
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, MessageProperties.findMessage("ERROR-03")));

        Optional<Subscription> subscriptionOptional = subscriptionRepository
                .findSubscriptionByUserAndNewsletter(user, newsletter);

        Subscription subscription = subscriptionOptional.orElseGet(() -> {
            Subscription subscriptionSaved = subscriptionRepository.save(new Subscription(user, newsletter));
            eventDispatcher.send(new SubscriptionEvent(subscriptionSaved));
            return subscriptionSaved;
        });


        return subscription.getId();

    }

}
