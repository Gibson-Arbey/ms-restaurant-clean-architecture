package co.clean_architecture.api.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkOrderAsDeliveredRequest {
    private Integer pin;
}
