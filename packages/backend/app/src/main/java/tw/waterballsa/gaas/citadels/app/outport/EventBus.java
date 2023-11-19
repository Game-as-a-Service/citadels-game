package tw.waterballsa.gaas.citadels.app.outport;

import tw.waterballsa.gaas.citadels.events.DomainEvent;

import java.util.List;

public interface EventBus {
    void broadcast(List<DomainEvent> events);
}
