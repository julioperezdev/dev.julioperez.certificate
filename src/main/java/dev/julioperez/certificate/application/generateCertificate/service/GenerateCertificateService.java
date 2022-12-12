package dev.julioperez.certificate.application.generateCertificate.service;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;
import dev.julioperez.certificate.domain.port.generateCertificate.GenerateCertificateOutputPort;

public class GenerateCertificateService implements GenerateCertificate{

    private final GenerateCertificateOutputPort generateCertificateOutputPort;

    public GenerateCertificateService(GenerateCertificateOutputPort generateCertificateOutputPort) {
        this.generateCertificateOutputPort = generateCertificateOutputPort;
    }

    @Override
    public CertificateGenerated generateCertificate(CertificateInformation certificateInformation) {
        return generateCertificateOutputPort.generateCertificate(certificateInformation);
    }
}

