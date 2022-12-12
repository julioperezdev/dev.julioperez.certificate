package dev.julioperez.certificate.domain.port.createStudentCertificate;

import dev.julioperez.certificate.domain.model.StudentCertificate;

public interface CreateStudentCertificateOutputPort {
    StudentCertificate createStudentCertificate(StudentCertificate studentCertificate);
}
