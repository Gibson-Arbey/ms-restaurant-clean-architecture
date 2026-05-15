package co.clean_architecture.model.traceability.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidTraceabilityException extends DomainException {
    public InvalidTraceabilityException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_TRACEABILITY";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
