package dev.julioperez.certificate.infrastructure.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RestResponse<T>{
    private Integer statusCode;
    private String status;
    private String message;
    private T body;

    public RestResponse(HttpStatus httpStatus){
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
    }

    public RestResponse(HttpStatus httpStatus, T body){
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.body = body;
    }

    public RestResponse(HttpStatus httpStatus, String message){
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public RestResponse(HttpStatus httpStatus, String message, T body){
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.message = message;
        this.body = body;
    }
}
