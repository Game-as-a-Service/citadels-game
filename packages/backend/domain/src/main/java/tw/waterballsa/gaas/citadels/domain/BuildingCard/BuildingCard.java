package tw.waterballsa.gaas.citadels.domain.BuildingCard;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Optional;

@Value
@AllArgsConstructor
public class BuildingCard {
    String name;
    int coins;
    Color color;

    public enum Color {
        PURPLE("特別地區"),
        GOLD("貴族地區"),
        RED("軍事地區"),
        BLUE("商業地區"),
        GREEN("宗教地區");
        private final String area;

        Color(String area) {
            this.area = area;
        }

        public static Optional<Color> fromString(String str) {
            for (Color color : Color.values()) {
                if(str.equalsIgnoreCase(color.toString())) {
                    return Optional.ofNullable(color);
                }
            }
            return Optional.empty();
        }
    }
}
