package co.clean_architecture.model.exception;

public class InvalidRequestException extends DomainException {
    public InvalidRequestException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_REQUEST";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
