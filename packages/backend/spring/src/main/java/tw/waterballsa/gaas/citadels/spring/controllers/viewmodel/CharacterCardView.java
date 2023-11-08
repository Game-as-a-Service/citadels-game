package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.CharacterCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CharacterCardView {
    private int sequence;
    private String name;

    public static CharacterCardView toViewModel(CharacterCard characterCard) {
        return (characterCard != null) ? new CharacterCardView(characterCard.getSequence(), characterCard.getName()) : null;
    }

    public static List<CharacterCardView> toViewModels(List<CharacterCard> characterCards) {
        return characterCards.stream().map(CharacterCardView::toViewModel).collect(Collectors.toList());
    }
}
