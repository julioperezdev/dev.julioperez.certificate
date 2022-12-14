package dev.julioperez.certificate.application.createStudentCertificate.service;

import dev.julioperez.certificate.application.createQrValidator.service.CreateQrValidator;
import dev.julioperez.certificate.application.generateCertificate.service.GenerateCertificate;
import dev.julioperez.certificate.domain.model.CertificateGenerated;
import dev.julioperez.certificate.domain.model.CertificateInformation;
import dev.julioperez.certificate.domain.model.StudentCertificate;
import dev.julioperez.certificate.domain.port.createStudentCertificate.CreateStudentCertificateOutputPort;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class CreateStudentCertificateService implements CreateStudentCertificate{
    private final CreateStudentCertificateOutputPort createStudentCertificateOutputPort;
    private final GenerateCertificate generateCertificate;
    private final CreateQrValidator createQrValidator;

    public CreateStudentCertificateService(CreateStudentCertificateOutputPort createStudentCertificateOutputPort, GenerateCertificate generateCertificate, CreateQrValidator createQrValidator) {
        this.createStudentCertificateOutputPort = createStudentCertificateOutputPort;
        this.generateCertificate = generateCertificate;
        this.createQrValidator = createQrValidator;
    }

    @Override
    public void createStudentCertificate(StudentCertificate studentCertificate) {
        log.info("empieza el createStudentCertificate");
        StudentCertificate studentCertificateCreated = createStudentCertificateOutputPort.createStudentCertificate(studentCertificate);
        log.info("termina el createStudentCertificate");
        //todo: create certificate
        String qrValidatorPath = createQrValidator.createQrValidator(studentCertificateCreated.id());
        log.info("se mapea el CertificateInformation");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formatter.format(studentCertificateCreated.createdAt());
        CertificateInformation certificateInformation = new CertificateInformation(
                studentCertificateCreated.id(),
                studentCertificateCreated.studentName(),
                studentCertificateCreated.courseName(),
                dateFormatted,
                qrValidatorPath);
        log.info("empieza el generateCertificate");
        CertificateGenerated certificateGenerated = generateCertificate.generateCertificate(certificateInformation);
        log.info("El certificado tuvo un resultado {} para el courseId {} ",
                certificateGenerated.isCreated(),
                certificateGenerated.certificateId());
    }
}
