package tw.waterballsa.gaas.citadels.spring.outport;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.waterballsa.gaas.citadels.app.outport.EventBus;
import tw.waterballsa.gaas.citadels.events.DomainEvent;
import tw.waterballsa.gaas.citadels.events.RoomEvent;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetSpecificRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SSeEventBusService implements EventBus {

    private final Map<String, List<SseEmitter>> roomIdToClientMap = new ConcurrentHashMap<>();

    public SseEmitter subscript(String roomId, String roomEvent) {
        SseEmitter sseEmitter = new SseEmitter(100000L);
        if ("room_event".equals(roomEvent)) {
            if (roomIdToClientMap.containsKey(roomId)) {
                roomIdToClientMap.get(roomId).add(sseEmitter);
            } else {
                List<SseEmitter> sseEmitters = new ArrayList<>();
                sseEmitters.add(sseEmitter);
                roomIdToClientMap.put(roomId, sseEmitters);
            }
        } else {
            throw new RuntimeException("not support event");
        }
        return sseEmitter;
    }

    @Override
    public void broadcast(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            if (event instanceof RoomEvent roomEvent) {
                List<SseEmitter> sseEmitters = roomIdToClientMap.get(roomEvent.getUpdatedRoom().getId());
                for (SseEmitter sseEmitter : sseEmitters) {
                    try {
                        GetSpecificRoomView view = new GetSpecificRoomView(LocalDateTime.now().toString(), "OK", "", RoomView.toViewModel((roomEvent.getUpdatedRoom())));
                        sseEmitter.send(view);
                    } catch(Exception e) {
                        e.printStackTrace();
                        sseEmitter.completeWithError(e);
                    }
                }
            }
        }
    }
}
