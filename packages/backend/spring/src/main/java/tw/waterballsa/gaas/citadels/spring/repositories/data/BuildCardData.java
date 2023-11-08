package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.BuildCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class BuildCardData {
    @Id
    private String name;
    private int coins;
    private BuildCard.Color color;

    public static BuildCardData toData(BuildCard buildCard) {
        return new BuildCardData(buildCard.getName(), buildCard.getCoins(), buildCard.getColor());
    }

    public static List<BuildCardData> toData(List<BuildCard> buildCards) {
        return buildCards.stream().map(BuildCardData::toData).collect(Collectors.toList());
    }

    public static BuildCard toDomain(BuildCardData buildCardData) {
        return new BuildCard(buildCardData.getName(), buildCardData.getCoins(), buildCardData.getColor());
    }

    public static List<BuildCard> toDomains(List<BuildCardData> buildCardDataList) {
        return buildCardDataList.stream().map(BuildCardData::toDomain).collect(Collectors.toList());
    }
}
