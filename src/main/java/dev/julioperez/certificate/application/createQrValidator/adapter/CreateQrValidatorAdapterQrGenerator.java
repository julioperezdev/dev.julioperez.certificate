package dev.julioperez.certificate.application.createQrValidator.adapter;

import dev.julioperez.certificate.domain.port.createQrValidator.CreateQrValidatorOutputPort;
import dev.julioperez.certificate.infrastructure.gateway.zxingQR.ZxingQr;

import java.util.UUID;

public class CreateQrValidatorAdapterQrGenerator implements CreateQrValidatorOutputPort {
    private final ZxingQr zxingQr;

    public CreateQrValidatorAdapterQrGenerator(ZxingQr zxingQr) {
        this.zxingQr = zxingQr;
    }

    @Override
    public String createQrValidator(UUID certificateId) {
        return zxingQr.createQrToCertificate(certificateId);
    }
}
