package com.lwelnicki.carrental.commons.events;

import io.vavr.collection.List;

public interface DomainEvents {

    void publish(DomainEvent event);

    default void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }
}
