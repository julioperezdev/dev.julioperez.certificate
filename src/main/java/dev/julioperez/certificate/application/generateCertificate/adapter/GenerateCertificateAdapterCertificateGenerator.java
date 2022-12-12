package dev.julioperez.certificate.application.generateCertificate.adapter;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;
import dev.julioperez.certificate.domain.port.generateCertificate.GenerateCertificateOutputPort;
import dev.julioperez.certificate.infrastructure.gateway.iTextRenderPdf.ITextRenderPdf;

public class GenerateCertificateAdapterCertificateGenerator implements GenerateCertificateOutputPort {
    private final ITextRenderPdf iTextRenderPdf;

    public GenerateCertificateAdapterCertificateGenerator(ITextRenderPdf iTextRenderPdf) {
        this.iTextRenderPdf = iTextRenderPdf;
    }

    @Override
    public CertificateGenerated generateCertificate(CertificateInformation certificateInformation) {
        return iTextRenderPdf.generatePdfFile(certificateInformation);
    }
}
