package co.clean_architecture.consumer.user.mapper;

import co.clean_architecture.consumer.user.response.UserResponse;
import co.clean_architecture.model.user.User;

public class UserResponseMapper {

    public static User toDomain(UserResponse response) {
        if(response == null) return null;
        return User.restore(
                response.getId(),
                response.getName(),
                response.getLastName(),
                response.getDocumentNumber(),
                response.getBirthDate(),
                response.getPhoneNumber(),
                response.getMail(),
                response.getStatus(),
                response.getRoleId()
        );
    }
}
