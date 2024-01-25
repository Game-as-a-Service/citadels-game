package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.RoleCard;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class RoleCardData {
    @Id
    private int sequence;
    private String name;

    public static RoleCardData toData(RoleCard roleCard) {
        return (roleCard != null) ? new RoleCardData(roleCard.getSequence(), roleCard.getName()) : null;
    }

    public static List<RoleCardData> toData(List<RoleCard> roleCards) {
        return roleCards.stream().map(RoleCardData::toData).collect(Collectors.toList());
    }

    public static RoleCard toDomain(RoleCardData characterCardData) {
        return characterCardData != null ? new RoleCard(characterCardData.getSequence(), characterCardData.getName()) : null;
    }

    public static Map<Integer, RoleCard> toDomains(List<RoleCardData> characterCardDataList) {
        Map<Integer, RoleCard> cards = new LinkedHashMap<>();
        characterCardDataList.forEach(characterCardData -> cards.put(characterCardData.getSequence(), toDomain(characterCardData)));
        return cards;
    }

    public static List<RoleCard> toDomainList(List<RoleCardData> characterCardDataList) {
        return characterCardDataList.stream().map(RoleCardData::toDomain).collect(Collectors.toList());
    }
}
