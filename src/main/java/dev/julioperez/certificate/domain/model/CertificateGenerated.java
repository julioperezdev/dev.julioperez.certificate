package dev.julioperez.certificate.domain.model;

import java.util.UUID;

public record CertificateGenerated(
        UUID certificateId,
        boolean isCreated,
        String certificateHash) {
}
