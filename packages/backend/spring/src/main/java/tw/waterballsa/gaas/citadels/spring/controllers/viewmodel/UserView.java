package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private String userId;
    private String userImage;
    private String userName;

    public static UserView toViewModel(User user) {
        return new UserView(user.getId(), user.getImage(), user.getName());
    }
}
