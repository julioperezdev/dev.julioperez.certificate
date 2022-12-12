package dev.julioperez.certificate.domain.exception;

import dev.julioperez.certificate.infrastructure.delivery.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QrBuilderCantCompleteProcess extends DomainError {
    public QrBuilderCantCompleteProcess(String errorMessage) {
        super(errorMessage);
        log.error("QrBuilderCantCompleteProcess");
    }
}
