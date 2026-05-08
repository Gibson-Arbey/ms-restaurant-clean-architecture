package co.clean_architecture.model.user.gateways;

import co.clean_architecture.model.user.User;

public interface UserRepository {

    Boolean existsOwnerById(Long userId);

    Boolean existsEmployeeById(Long userId);

    User findById(Long userId);
}
