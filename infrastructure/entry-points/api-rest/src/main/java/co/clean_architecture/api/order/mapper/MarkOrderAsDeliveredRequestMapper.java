package co.clean_architecture.api.order.mapper;

import co.clean_architecture.api.order.request.MarkOrderAsDeliveredRequest;
import co.clean_architecture.usecase.order.command.MarkOrderAsDeliveredCommand;

public class MarkOrderAsDeliveredRequestMapper {

    public static MarkOrderAsDeliveredCommand toCommand(MarkOrderAsDeliveredRequest request) {
        if(request == null) return null;
        return new MarkOrderAsDeliveredCommand(request.getPin());
    }
}
