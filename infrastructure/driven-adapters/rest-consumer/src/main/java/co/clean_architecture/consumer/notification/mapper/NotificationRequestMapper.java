package co.clean_architecture.consumer.notification.mapper;

import co.clean_architecture.consumer.notification.request.NotificationRequest;
import co.clean_architecture.model.notification.Notification;

public class NotificationRequestMapper {

    public static NotificationRequest toRequest(Notification notification) {
        if(notification == null) return null;
        return NotificationRequest
                .builder()
                .recipient(notification.getRecipient())
                .message(notification.getMessage())
                .build();
    }
}
