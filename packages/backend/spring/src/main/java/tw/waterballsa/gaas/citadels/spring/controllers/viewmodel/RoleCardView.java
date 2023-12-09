package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.RoleCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class RoleCardView {
    private int sequence;
    private String name;

    public static RoleCardView toViewModel(RoleCard roleCard) {
        return (roleCard != null) ? new RoleCardView(roleCard.getSequence(), roleCard.getName()) : null;
    }

    public static List<RoleCardView> toViewModels(List<RoleCard> roleCards) {
        return roleCards.stream().map(RoleCardView::toViewModel).collect(Collectors.toList());
    }
}
