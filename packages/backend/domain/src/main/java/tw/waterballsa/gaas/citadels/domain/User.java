package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.util.UUID.randomUUID;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class User {
    private final String id;
    private final String name;
    private final String imageName;

    public User(String name, String useImage) {
        this.id = randomUUID().toString();
        this.name = name;
        this.imageName = useImage;
    }

}
