package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.BuildCard;
import tw.waterballsa.gaas.citadels.domain.CharacterCard;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PlayerView {
    private String id;
    private String name;
    private String imageName;
    private Integer coins;
    private List<BuildCardView> buildCards;
    private CharacterCardView characterCard;
    private Boolean hasCrown;

    public static List<PlayerView> toViewModels(List<Player> players) {
        return players.stream().map(PlayerView::toViewModel).collect(Collectors.toList());
    }


    public static PlayerView toViewModel(Player player) {
        List<BuildCardView> buildCardViews = BuildCardView.toViewModels(player.getBuildCards());
        CharacterCardView characterCardView = CharacterCardView.toViewModel(player.getCharacterCard());
        return new PlayerView(player.getId(),player.getName(),player.getImageName(),player.getCoins(),buildCardViews,characterCardView,player.getHasCrown());
    }
}
