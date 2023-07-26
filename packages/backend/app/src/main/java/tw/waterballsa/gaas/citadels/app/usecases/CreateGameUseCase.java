package tw.waterballsa.gaas.citadels.app.usecases;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.Game;

import javax.inject.Named;



@Named
public class CreateGameUseCase {

    public void excute(Request request){
        Game game = Game.builder().roomName(request.name).holderName(request.holderName).build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        public String name;
        public String holderName;
    }
}
