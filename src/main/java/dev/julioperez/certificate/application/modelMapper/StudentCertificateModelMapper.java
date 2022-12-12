package dev.julioperez.certificate.application.modelMapper;

import dev.julioperez.certificate.domain.dto.StudentCertificateToCreate;
import dev.julioperez.certificate.domain.model.StudentCertificate;
import dev.julioperez.certificate.domain.port.mapper.StudentCertificateMapper;
import dev.julioperez.certificate.infrastructure.repository.model.StudentCertificateEntity;

import java.util.Date;

public class StudentCertificateModelMapper implements StudentCertificateMapper {
    @Override
    public StudentCertificate toStudentCertificate(StudentCertificateToCreate studentCertificateToCreate) {
        Date createdAt = new Date();
        return new StudentCertificate(
                studentCertificateToCreate.id(),
                studentCertificateToCreate.studentId(),
                studentCertificateToCreate.studentName(),
                studentCertificateToCreate.courseId(),
                studentCertificateToCreate.courseName(),
                createdAt,
                null);
    }

    @Override
    public StudentCertificate toStudentCertificate(StudentCertificateEntity studentCertificateEntity) {
        return new StudentCertificate(
                studentCertificateEntity.getId(),
                studentCertificateEntity.getStudentId(),
                studentCertificateEntity.getStudentName(),
                studentCertificateEntity.getCourseId(),
                studentCertificateEntity.getCourseName(),
                studentCertificateEntity.getCreatedAt(),
                studentCertificateEntity.getCertificateUrl());
    }

    @Override
    public StudentCertificateEntity toStudentCertificateEntity(StudentCertificate studentCertificate) {
        return new StudentCertificateEntity(
                studentCertificate.id(),
                studentCertificate.studentId(),
                studentCertificate.studentName(),
                studentCertificate.courseId(),
                studentCertificate.courseName(),
                studentCertificate.createdAt(),
                studentCertificate.certificateUrl());
    }
}

