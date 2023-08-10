package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static java.lang.String.valueOf;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class User {
    private final String id;
    private final String name;
    private final String imageName;

    public User(String useName, String useImage) {
        this.id = generateUniqueId();
        this.name = useName;
        this.imageName = useImage;
    }

    private String generateUniqueId() {
        return valueOf(UUID.randomUUID());
    }
}
