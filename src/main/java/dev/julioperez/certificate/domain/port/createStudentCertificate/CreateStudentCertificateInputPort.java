package dev.julioperez.certificate.domain.port.createStudentCertificate;

import dev.julioperez.certificate.domain.dto.StudentCertificateToCreate;

public interface CreateStudentCertificateInputPort {
    void createStudentCertificate(StudentCertificateToCreate studentCertificateToCreate);
}
