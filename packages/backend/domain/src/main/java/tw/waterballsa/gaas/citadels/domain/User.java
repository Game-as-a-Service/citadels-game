package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String name;
    String image;

    public User(String name, String image) {
        this(randomUUID().toString(), name, image);
    }
}
