package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class EmployeeNotAssignedToOrderException extends DomainException {
    public EmployeeNotAssignedToOrderException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EMPLOYEE_NOT_ASSIGNED_TO_ORDER";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
