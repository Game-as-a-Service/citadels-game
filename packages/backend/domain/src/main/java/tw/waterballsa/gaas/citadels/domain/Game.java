package tw.waterballsa.gaas.citadels.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Game {
    private String id;
    private String roomName;
    private String holderName;

}
