package dev.julioperez.certificate.domain.port.createQrValidator;

import java.util.UUID;

public interface CreateQrValidatorOutputPort {
    String createQrValidator(UUID certificateId);
}
