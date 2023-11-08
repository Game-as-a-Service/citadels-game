package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.CharacterCard;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class CharacterCardData {
    @Id
    private int sequence;
    private String name;

    public static CharacterCardData toData(CharacterCard characterCard) {
        return (characterCard != null) ? new CharacterCardData(characterCard.getSequence(), characterCard.getName()) : null;
    }

    public static List<CharacterCardData> toData(List<CharacterCard> characterCards) {
        return characterCards.stream().map(CharacterCardData::toData).collect(Collectors.toList());
    }

    public static CharacterCard toDomain(CharacterCardData characterCardData) {
        return characterCardData != null ? new CharacterCard(characterCardData.getSequence(), characterCardData.getName()) : null;
    }

    public static Map<Integer, CharacterCard> toDomains(List<CharacterCardData> characterCardDataList) {
        Map<Integer, CharacterCard> cards = new LinkedHashMap<>();
        characterCardDataList.forEach(characterCardData -> cards.put(characterCardData.getSequence(), toDomain(characterCardData)));
        return cards;
    }

    public static List<CharacterCard> toDomainList(List<CharacterCardData> characterCardDataList) {
        return characterCardDataList.stream().map(CharacterCardData::toDomain).collect(Collectors.toList());
    }
}
