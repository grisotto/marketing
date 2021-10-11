package com.marketing.subscription.repository;

import com.marketing.subscription.entity.Newsletter;
import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findSubscriptionByUserAndNewsletter(User user, Newsletter newsletter);
}
