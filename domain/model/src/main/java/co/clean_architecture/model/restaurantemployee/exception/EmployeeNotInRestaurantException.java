package co.clean_architecture.model.restaurantemployee.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class EmployeeNotInRestaurantException extends DomainException {
    public EmployeeNotInRestaurantException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EMPLOYEE_NOT_IN_RESTAURANT";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
