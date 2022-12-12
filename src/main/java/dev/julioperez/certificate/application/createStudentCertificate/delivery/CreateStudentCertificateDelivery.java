package dev.julioperez.certificate.application.createStudentCertificate.delivery;

import dev.julioperez.certificate.application.createStudentCertificate.service.CreateStudentCertificate;
import dev.julioperez.certificate.domain.dto.StudentCertificateToCreate;
import dev.julioperez.certificate.domain.model.StudentCertificate;
import dev.julioperez.certificate.domain.port.createStudentCertificate.CreateStudentCertificateInputPort;
import dev.julioperez.certificate.domain.port.mapper.StudentCertificateMapper;

public class CreateStudentCertificateDelivery implements CreateStudentCertificateInputPort {
    private final CreateStudentCertificate createStudentCertificate;
    private final StudentCertificateMapper studentCertificateMapper;

    public CreateStudentCertificateDelivery(CreateStudentCertificate createStudentCertificate, StudentCertificateMapper studentCertificateMapper) {
        this.createStudentCertificate = createStudentCertificate;
        this.studentCertificateMapper = studentCertificateMapper;
    }

    @Override
    public void createStudentCertificate(StudentCertificateToCreate studentCertificateToCreate) {
        StudentCertificate studentCertificate = studentCertificateMapper.toStudentCertificate(studentCertificateToCreate);
        createStudentCertificate.createStudentCertificate(studentCertificate);
    }
}
