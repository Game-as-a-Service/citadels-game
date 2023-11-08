package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuildCard {
    private String name;
    private int coins;
    private Color color;

    public enum Color {
        PURPLE("特別地區"),
        YELLOW("貴族地區"),
        RED("軍事地區"),
        BLUE("商業地區"),
        GREEN("宗教地區");
        private final String area;

        Color(String area) {
            this.area = area;
        }
    }

}

