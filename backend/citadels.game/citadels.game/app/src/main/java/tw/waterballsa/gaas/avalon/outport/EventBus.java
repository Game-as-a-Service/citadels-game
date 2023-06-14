package tw.waterballsa.gaas.avalon.outport;

import tw.waterballsa.gaas.avalon.domain.DomainEvent;

import java.util.List;

/**
 * @author johnny@waterballsa.tw
 */
public interface EventBus {
    void broadcast(List<DomainEvent> events);
}
