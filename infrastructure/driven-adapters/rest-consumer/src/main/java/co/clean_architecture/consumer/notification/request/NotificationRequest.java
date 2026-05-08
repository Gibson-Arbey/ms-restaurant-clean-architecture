package co.clean_architecture.consumer.notification.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationRequest {
    private String recipient;

    private String message;

}
