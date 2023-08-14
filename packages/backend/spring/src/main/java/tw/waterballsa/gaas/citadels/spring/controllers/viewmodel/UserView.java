package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.User;

@Data
@AllArgsConstructor
public class UserView {
    private String userId;
    private String userName;
    private String userImage;

    public static UserView toViewModel(User holder) {
        return new UserView(holder.getId(), holder.getName(), holder.getImageName());
    }
}
