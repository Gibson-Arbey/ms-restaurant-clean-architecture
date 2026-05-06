package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class OrderNotExistsException extends DomainException {

    public OrderNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "ORDER_NOT_EXISTS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
