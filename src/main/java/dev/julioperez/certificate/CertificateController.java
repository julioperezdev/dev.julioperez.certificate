package dev.julioperez.certificate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("certificate")
public class CertificateController {

    @GetMapping
    public String hello(){
        return "helloGet";
    }
    @PostMapping("/post")
    public String helloPost(){
        return "helloPost";
    }
}
