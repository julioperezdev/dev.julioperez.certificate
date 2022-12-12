package dev.julioperez.certificate.domain.exception;

import dev.julioperez.certificate.infrastructure.delivery.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CertificateException extends DomainError {
    public CertificateException(String errorMessage) {
        super(errorMessage);
        log.error("Certificate Exception");
    }
}
