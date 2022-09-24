package com.lwelnicki.carrental.commons.events.publisher;

import com.lwelnicki.carrental.commons.events.DomainEvent;
import com.lwelnicki.carrental.commons.events.DomainEvents;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@AllArgsConstructor
public class ForwardingDomainEventPublisher implements DomainEvents {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
