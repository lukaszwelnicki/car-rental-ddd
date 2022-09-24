package com.lwelnicki.carrental.commons.events.publisher;

import com.lwelnicki.carrental.commons.events.DomainEvent;
import io.vavr.collection.List;

public interface EventsStorage {

    void save(DomainEvent event);

    List<DomainEvent> toPublish();

    void published(List<DomainEvent> events);
}
