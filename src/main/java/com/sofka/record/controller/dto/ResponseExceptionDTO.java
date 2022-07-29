package com.sofka.record.controller.dto;

import com.sofka.record.utility.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseExceptionDTO {

    private Response response;
    private HttpStatus httpStatus;

    public ResponseExceptionDTO(Response response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }
}
