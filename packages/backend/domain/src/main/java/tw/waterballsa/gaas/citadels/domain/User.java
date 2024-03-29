package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

import static java.util.UUID.randomUUID;

@Getter
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String imageName;

    public User(String name, String image) {
        this(randomUUID().toString(), name, image);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(imageName, user.imageName);
    }
}