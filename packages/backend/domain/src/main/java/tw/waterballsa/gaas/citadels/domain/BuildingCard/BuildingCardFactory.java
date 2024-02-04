package tw.waterballsa.gaas.citadels.domain.BuildingCard;

import java.util.Optional;

public interface BuildingCardFactory {

    public enum Type {
        TEMPLE,
        CHURCH,
        MONASTERY,
        CATHEDRAL,
        WATCHTOWER,
        PRISON,
        BATTLEFIELD,
        FORTRESS,
        MANOR,
        CASTLE,
        PALACE,
        TAVERN,
        INN,
        MARKET,
        BOATHOUSE,
        HARBOR,
        TOWN_HALL,
        LIBRARY,
        DRAGON_GATE,
        GRAVEYARD,
        GHOST_TOWN,
        SCHOOL_OF_MAGIC,
        LABORATORY,
        SMITHY,
        OBSERVATORY,
        UNIVERSITY,
        BASTION,
        GREAT_WALL;

        public static Optional<Type> fromInt(int value) {
            for(Type type : Type.values()) {
                if(type.ordinal() == value) {
                    return Optional.ofNullable(type);
                }
            }
            return Optional.empty();
        }

        public static Optional<Type> fromString(String str) {
            for (Type type : Type.values()) {
                if(str.equals(type.toString())) {
                    return Optional.ofNullable(type);
                }
            }
            return Optional.empty();
        }
    }

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

    public Optional<BuildingCard> createBuildingCard(Type type);
}


