package dev.julioperez.certificate.infrastructure.repository.dao;

import dev.julioperez.certificate.infrastructure.repository.model.StudentCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentCertificateDao extends JpaRepository<StudentCertificateEntity, UUID> {

    Optional<StudentCertificateEntity> findByStudentIdAndCourseId(UUID studentId, UUID courseId);

}
