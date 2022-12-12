package dev.julioperez.certificate.infrastructure.gateway.iTextRenderPdf;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;

public interface ITextRenderPdf {
    CertificateGenerated generatePdfFile(CertificateInformation certificateInformation);
}
