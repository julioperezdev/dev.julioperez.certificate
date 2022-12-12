package dev.julioperez.certificate.infrastructure.gateway.zxingQR;

import java.util.UUID;

public interface ZxingQr {
    String createQrToCertificate(UUID certificateId);
}
