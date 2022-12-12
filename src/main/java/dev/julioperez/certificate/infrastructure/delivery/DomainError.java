package dev.julioperez.certificate.infrastructure.delivery;

public class DomainError extends RuntimeException{
    private final String errorMessage;

    public DomainError(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String errorMessage() {
        return errorMessage;
    }
}
