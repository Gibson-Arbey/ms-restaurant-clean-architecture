package co.clean_architecture.model.notification;

import lombok.Getter;

@Getter
public class Notification {

    private final String recipient;

    private final String message;

    private Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public static Notification create(String recipient, String message) {
        return new Notification(recipient, message);
    }
}
