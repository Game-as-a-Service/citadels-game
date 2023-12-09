package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PlayerView {
    private String id;
    private String name;
    private String imageName;
    private Integer coins;
    private List<BuildingCardView> buildingCardViews;
    private RoleCardView roleCardView;
    private Boolean hasCrown;

    public static List<PlayerView> toViewModels(List<Player> players) {
        return players.stream().map(PlayerView::toViewModel).collect(Collectors.toList());
    }


    public static PlayerView toViewModel(Player player) {
        List<BuildingCardView> buildingCardViews = BuildingCardView.toViewModels(player.getBuildingCards());
        RoleCardView roleCardView = RoleCardView.toViewModel(player.getRoleCard());
        return new PlayerView(player.getId(),player.getName(),player.getImageName(),player.getCoins(),buildingCardViews,roleCardView,player.getHasCrown());
    }
}
