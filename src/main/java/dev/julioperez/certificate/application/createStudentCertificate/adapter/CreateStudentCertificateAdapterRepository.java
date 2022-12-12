package dev.julioperez.certificate.application.createStudentCertificate.adapter;

import dev.julioperez.certificate.domain.model.StudentCertificate;
import dev.julioperez.certificate.domain.port.createStudentCertificate.CreateStudentCertificateOutputPort;
import dev.julioperez.certificate.domain.port.mapper.StudentCertificateMapper;
import dev.julioperez.certificate.infrastructure.repository.dao.StudentCertificateDao;
import dev.julioperez.certificate.infrastructure.repository.model.StudentCertificateEntity;

public class CreateStudentCertificateAdapterRepository implements CreateStudentCertificateOutputPort {
    private final StudentCertificateDao studentCertificateDao;
    private final StudentCertificateMapper studentCertificateMapper;

    public CreateStudentCertificateAdapterRepository(StudentCertificateDao studentCertificateDao, StudentCertificateMapper studentCertificateMapper) {
        this.studentCertificateDao = studentCertificateDao;
        this.studentCertificateMapper = studentCertificateMapper;
    }

    @Override
    public StudentCertificate createStudentCertificate(StudentCertificate studentCertificate) {
        StudentCertificateEntity studentCertificateEntity = studentCertificateMapper.toStudentCertificateEntity(studentCertificate);
        studentCertificateDao.saveAndFlush(studentCertificateEntity);
        return studentCertificateMapper.toStudentCertificate(studentCertificateEntity);
    }
}
