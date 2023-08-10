package tw.waterballsa.gaas.citadels.spring.repositories.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class UserData {
    @Id
    private String id;
    private String name;
    private String imageName;

    public static UserData toData(User user) {
        return new UserData(user.getId(), user.getName(), user.getImageName());
    }

    public static List<UserData> toDatas(List<User> users) {
        return users.stream().map(UserData::toData)
                               .collect(Collectors.toList());
    }

    public static User toDomain(UserData userData) {
        return new User(userData.getId(),userData.getName(), userData.getImageName());
    }

    public static Map<String,User> toDomains(List<UserData> users) {
        Map<String,User> maps = new LinkedHashMap<>();
        users.forEach(userData -> maps.put(userData.getId(),toDomain(userData)));
        return maps;

//        return users.stream().map(UserData::toDomain)
//                                .collect(Collectors.toList());
    }
}
