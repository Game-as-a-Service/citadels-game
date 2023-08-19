package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private String userId;
    private String userImage;
    private String userName;

    public static UserView toViewModel(User user) {
        return new UserView(user.getId(), user.getImageName(), user.getName());
    }

    public static List<UserView> toViewModel(List<User> user) {
        return user.stream().map(UserView::toViewModel).collect(Collectors.toList());
    }
}
