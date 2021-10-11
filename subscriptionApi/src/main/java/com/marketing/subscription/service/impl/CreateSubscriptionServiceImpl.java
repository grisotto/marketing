package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.NewsletterRepository;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.repository.UserRepository;
import com.marketing.subscription.service.api.CreateSubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class CreateSubscriptionServiceImpl implements CreateSubscriptionService {

    private final NewsletterRepository newsletterRepository;

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    public CreateSubscriptionServiceImpl(NewsletterRepository newsletterRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.newsletterRepository = newsletterRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public String create(SubscriptionDTO subscriptionDTO) {
        try {
            Optional<User> userEmail = userRepository.findBySimpleNaturalId(subscriptionDTO.getEmail());

            User user = userEmail.orElseGet(() -> {
                var userSaved = new User(subscriptionDTO.getEmail(), subscriptionDTO.getFirstName(), subscriptionDTO.getGender(), subscriptionDTO.getDateOfBirth(), subscriptionDTO.isConsent());
                userRepository.save(userSaved);
                return userSaved;
            });

            Optional<Newsletter> newsletterOptional = newsletterRepository.findById(subscriptionDTO.getNewsletterId());
            //TODO: Throw NewsletterNotFound
            Newsletter newsletter = newsletterOptional.orElseThrow();

            Optional<Subscription> subscriptionOptional = subscriptionRepository.findSubscriptionByUserAndNewsletter(user, newsletter);
            Subscription subscription = subscriptionOptional.orElseGet(() -> {
                var save = new Subscription(user, newsletter);
                return subscriptionRepository.save(save);
            });


            return subscription.getId().toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed");
        }
    }

}
