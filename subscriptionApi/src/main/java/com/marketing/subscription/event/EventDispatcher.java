package com.marketing.subscription.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;

    private final String mailerExchange;

    private final String mailerSubscriptionRoutingKey;

    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${mailer.exchange}") final String mailerExchange,
                    @Value("${mailer.subscription.key}") final String mailerSubscriptionRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.mailerExchange = mailerExchange;
        this.mailerSubscriptionRoutingKey = mailerSubscriptionRoutingKey;
    }

    public void send(final SubscriptionEvent subscriptionEvent) {
        rabbitTemplate.convertAndSend(
                mailerExchange,
                mailerSubscriptionRoutingKey,
                subscriptionEvent);
    }
}
