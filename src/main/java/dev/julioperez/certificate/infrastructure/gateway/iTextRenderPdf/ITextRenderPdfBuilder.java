package dev.julioperez.certificate.infrastructure.gateway.iTextRenderPdf;

import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ITextRenderPdfBuilder implements ITextRenderPdf{
    private final TemplateEngine templateEngine;
    @Value("${julioperez.certificate.bucket.directory}")
    private String bucketDirectory;
    @Value("${julioperez.certificate.template.name}")
    private String templateName;

    public ITextRenderPdfBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public CertificateGenerated generatePdfFile(CertificateInformation certificateInformation) {
        Map<String, Object> certificateData = buildCertificateData(certificateInformation);
        Context context = buildContext(certificateData);
        String htmlContent = templateEngine.process(templateName, context);
        String fileName = buildFileName(certificateInformation);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(bucketDirectory.concat(fileName));
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new CertificateGenerated(
                    certificateInformation.certificateId(),
                    Boolean.FALSE,
                    null);
        }
        return new CertificateGenerated(
                certificateInformation.certificateId(),
                Boolean.TRUE,
                null);
    }

    private Map<String, Object> buildCertificateData(CertificateInformation certificateInformation){
        Map<String, Object> certificateData = new HashMap<>();
        certificateData.put("certificateInformation", certificateInformation);
        return certificateData;
    }

    private Context buildContext(Map<String, Object> certificateData){
        Context context = new Context();
        context.setVariables(certificateData);
        return context;
    }

    private String buildFileName(CertificateInformation certificateInformation){
        final String CERTIFICATE = "certificate-";
        final String PDF_FORMAT = ".pdf";
        return CERTIFICATE
                .concat(certificateInformation.certificateId().toString())
                .concat(PDF_FORMAT);
    }
}
