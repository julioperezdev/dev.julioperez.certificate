package dev.julioperez.certificate.domain.dto;

import java.util.Objects;
import java.util.UUID;

public record StudentCertificateToCreate(UUID id,
                                         UUID studentId,
                                         String studentName,
                                         UUID courseId,
                                         String courseName) {
    public boolean isInvalidFields(){
        return Objects.isNull(courseId()) ||
                Objects.isNull(courseName()) ||
                Objects.isNull(studentId()) ||
                Objects.isNull(studentName());
    }
}
