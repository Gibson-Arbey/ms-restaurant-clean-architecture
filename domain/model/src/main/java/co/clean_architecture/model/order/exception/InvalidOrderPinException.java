package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidOrderPinException extends DomainException {
    public InvalidOrderPinException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_ORDER_PIN_EXCEPTION";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
