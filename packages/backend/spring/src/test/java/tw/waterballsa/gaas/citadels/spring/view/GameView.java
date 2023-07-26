package tw.waterballsa.gaas.citadels.spring.view;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.Game;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameView {
    public String id;
    public String roomName;
    public String holderName;

    public static GameView toViewModel(Game game){
        return new GameView(game.getId(),game.getRoomName(), game.getHolderName());
    }


}
