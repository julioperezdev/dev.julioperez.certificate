package dev.julioperez.certificate.application.createQrValidator.service;

import dev.julioperez.certificate.domain.port.createQrValidator.CreateQrValidatorOutputPort;

import java.util.UUID;

public class CreateQrValidatorService implements CreateQrValidator{
    private final CreateQrValidatorOutputPort createQrValidatorOutputPort;

    public CreateQrValidatorService(CreateQrValidatorOutputPort createQrValidatorOutputPort) {
        this.createQrValidatorOutputPort = createQrValidatorOutputPort;
    }

    @Override
    public String createQrValidator(UUID certificateId) {
        return createQrValidatorOutputPort.createQrValidator(certificateId);
    }
}
