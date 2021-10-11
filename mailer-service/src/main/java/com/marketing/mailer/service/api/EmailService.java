package com.marketing.mailer.service.api;

import com.marketing.mailer.event.SubscriptionEvent;

public interface EmailService {
    void sendCreateSubscription(SubscriptionEvent event);

    void sendCancelSubscription(SubscriptionEvent event);

}
