package com.lwelnicki.carrental.commons.events.publisher;

import com.lwelnicki.carrental.commons.events.DomainEvents;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventsConfig {

    @Bean
    DomainEvents domainEvents(ApplicationEventPublisher applicationEventPublisher) {
        return new ForwardingDomainEventPublisher(applicationEventPublisher);
    }
}
