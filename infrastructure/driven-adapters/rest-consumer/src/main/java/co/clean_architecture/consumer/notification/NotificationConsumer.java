package co.clean_architecture.consumer.notification;

import co.clean_architecture.consumer.notification.mapper.NotificationRequestMapper;
import co.clean_architecture.consumer.notification.request.NotificationRequest;
import co.clean_architecture.model.notification.Notification;
import co.clean_architecture.model.notification.gateways.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class NotificationConsumer implements NotificationRepository {

    private final RestClient notificationRestClient;

    public NotificationConsumer(@Qualifier("notificationRestClient") RestClient notificationRestClient) {
        this.notificationRestClient = notificationRestClient;
    }

    @Override
    public boolean sendNotification(Notification notification) {
        try {
            NotificationRequest notificationRequest = NotificationRequestMapper.toRequest(notification);
            if(notificationRequest == null) {
                return false;
            }
            notificationRestClient.post()
                .uri("/api/v1/notification")
                .body(notificationRequest)
                .retrieve()
                .toBodilessEntity();
            return true;
        } catch (Exception e) {
            log.error("Error consuming user service: {}", e.getMessage());
            return false;
        }
    }
}
