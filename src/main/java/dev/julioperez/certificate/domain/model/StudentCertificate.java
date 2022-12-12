package dev.julioperez.certificate.domain.model;

import java.util.Date;
import java.util.UUID;

public record StudentCertificate(
        UUID id,
        UUID studentId,
        String studentName,
        UUID courseId,
        String courseName,
        Date createdAt,
        String certificateUrl) {
}
