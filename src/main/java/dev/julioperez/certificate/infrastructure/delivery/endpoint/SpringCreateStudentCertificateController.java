package dev.julioperez.certificate.infrastructure.delivery.endpoint;

import dev.julioperez.certificate.domain.dto.StudentCertificateToCreate;
import dev.julioperez.certificate.domain.port.createStudentCertificate.CreateStudentCertificateInputPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("certificate")
public class SpringCreateStudentCertificateController {
    private final CreateStudentCertificateInputPort createStudentCertificateInputPort;

    public SpringCreateStudentCertificateController(CreateStudentCertificateInputPort createStudentCertificateInputPort) {
        this.createStudentCertificateInputPort = createStudentCertificateInputPort;
    }

    @PostMapping("/create")
    public String createStudentCertificate(@RequestBody StudentCertificateToCreate studentCertificateToCreate){
        createStudentCertificateInputPort.createStudentCertificate(studentCertificateToCreate);
        return "created";
    }
}
