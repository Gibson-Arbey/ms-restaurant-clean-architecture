package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidOrderStatusException extends DomainException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_ORDER_STATUS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
