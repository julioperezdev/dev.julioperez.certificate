package dev.julioperez.certificate.infrastructure.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;


import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "STUDENT_CERTIFICATE", schema = "CERTIFICATE")
public class StudentCertificateEntity {

    @Id
    //@JdbcType(value = "org.hibernate.type.UUIDJdbcType")
    //@Type( = "org.hibernate.type.UUIDCharType")
    //@Type(value = )
    private UUID id;
    @Column(name="STUDENT_ID",nullable = false)
    //@Type(type = "org.hibernate.type.UUIDCharType")
    private UUID studentId;
    @Column(name="STUDENT_NAME",nullable = false)
    private String studentName;
    @Column(name="COURSE_ID",nullable = false)
    //@Type(type = "org.hibernate.type.UUIDCharType")
    private UUID courseId;
    @Column(name="COURSE_NAME",nullable = false)
    private String courseName;
    @Column(name="CREATED_AT",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name="CERTIFICATE_URL")
    private String certificateUrl;
}

