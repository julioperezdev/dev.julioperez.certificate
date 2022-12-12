package dev.julioperez.certificate.domain.port.generateCertificate;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;

public interface GenerateCertificateOutputPort {
    CertificateGenerated generateCertificate(CertificateInformation certificateInformation);
}
