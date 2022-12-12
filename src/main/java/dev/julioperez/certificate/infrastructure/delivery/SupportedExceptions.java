package dev.julioperez.certificate.infrastructure.delivery;

import dev.julioperez.certificate.domain.exception.CertificateException;
import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    //COURSE_CREATOR_REQUEST_DONT_HAVE_REQUIREMENT(CourseCreatorRequestDontHaveRequirementException.class, HttpStatus.PRECONDITION_FAILED),
    //STUDENT_CERTIFICATE_DONT_HAVE_VALID_FIELD(StudentCertificateDontHaveValidField.class, HttpStatus.PRECONDITION_FAILED);
    CERTIFICATE_EXCEPTION(CertificateException.class, HttpStatus.PRECONDITION_FAILED);

    private Class<? extends DomainError> exceptionClass;
    private HttpStatus status;
    private SupportedExceptions(Class<? extends DomainError>exception, HttpStatus status){
        this.exceptionClass = exception;
        this.status = status;
    }

    public Class <? extends DomainError> getExceptionClass(){
        return this.exceptionClass;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}