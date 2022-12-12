package dev.julioperez.certificate.domain.port.mapper;

import dev.julioperez.certificate.domain.dto.StudentCertificateToCreate;
import dev.julioperez.certificate.domain.model.StudentCertificate;
import dev.julioperez.certificate.infrastructure.repository.model.StudentCertificateEntity;

public interface StudentCertificateMapper {
    StudentCertificate toStudentCertificate(StudentCertificateToCreate studentCertificateToCreate);
    StudentCertificate toStudentCertificate(StudentCertificateEntity studentCertificateEntity);
    StudentCertificateEntity toStudentCertificateEntity(StudentCertificate studentCertificate);
}
