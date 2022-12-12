package dev.julioperez.certificate.application.generateCertificate.service;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;

public interface GenerateCertificate {
    CertificateGenerated generateCertificate(CertificateInformation certificateInformation);
}
