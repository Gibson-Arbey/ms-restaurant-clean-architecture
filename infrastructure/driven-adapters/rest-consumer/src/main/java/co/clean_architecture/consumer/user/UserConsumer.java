package co.clean_architecture.consumer.user;

import co.clean_architecture.consumer.user.mapper.UserResponseMapper;
import co.clean_architecture.consumer.user.response.UserResponse;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class UserConsumer implements UserRepository {

    private final RestClient userRestClient;
    public UserConsumer(@Qualifier("userRestClient") RestClient userRestClient) {
        this.userRestClient = userRestClient;
    }


    @Override
    public Boolean existsOwnerById(Long userId) {
        try {
            Boolean response = userRestClient.get()
                    .uri("/api/v1/user/{userId}/exists-owner", userId)
                    .retrieve()
                    .body(Boolean.class);

            return Boolean.TRUE.equals(response);
        } catch (Exception e) {
            log.error("Error consuming user service: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean existsEmployeeById(Long userId) {
        try {
            Boolean response = userRestClient.get()
                    .uri("/api/v1/user/{userId}/exists-employee", userId)
                    .retrieve()
                    .body(Boolean.class);

            return Boolean.TRUE.equals(response);
        } catch (Exception e) {
            log.error("Error consuming user service: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public User findById(Long userId) {
        try {
            UserResponse response = userRestClient.get()
                    .uri("/api/v1/user/{userId}", userId)
                    .retrieve()
                    .body(UserResponse.class);

            return UserResponseMapper.toDomain(response);
        } catch (Exception e) {
            log.error("Error consuming user service: {}", e.getMessage());
            return null;
        }
    }
}
