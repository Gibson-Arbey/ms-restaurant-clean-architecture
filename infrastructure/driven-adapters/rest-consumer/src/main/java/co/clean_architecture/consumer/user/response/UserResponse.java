package co.clean_architecture.consumer.user.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String name;

    private String lastName;

    private String documentNumber;

    private LocalDate birthDate;

    private String phoneNumber;

    private String mail;

    private String status;

    private Long roleId;
}
