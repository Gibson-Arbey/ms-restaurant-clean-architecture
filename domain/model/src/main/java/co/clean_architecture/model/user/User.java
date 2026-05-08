package co.clean_architecture.model.user;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class User {

    private final Long id;

    private final String name;

    private final String lastName;

    private final String documentNumber;

    private final LocalDate birthDate;

    private final String phoneNumber;

    private final String mail;

    private final String status;

    private final Long roleId;

    private User(Long id, String name, String lastName, String documentNumber, LocalDate birthDate, String phoneNumber, String mail, String status, Long roleId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.status = status;
        this.roleId = roleId;
    }

    public static User restore(Long id, String name, String lastName, String documentNumber, LocalDate birthDate, String phoneNumber, String mail, String status, Long roleId) {
        return new User(id, name, lastName, documentNumber, birthDate, phoneNumber, mail, status, roleId);
    }

}
