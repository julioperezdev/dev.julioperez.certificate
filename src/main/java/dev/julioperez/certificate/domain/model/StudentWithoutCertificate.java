package dev.julioperez.certificate.domain.model;

import java.util.UUID;

public record StudentWithoutCertificate(UUID id, UUID studentId, String studentName, UUID courseId, String courseName) {
}
