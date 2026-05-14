package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class CustomerNotOwnerOfOrderException extends DomainException {
    public CustomerNotOwnerOfOrderException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "CUSTOMER_NOT_OWNER_OF_ORDER";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
