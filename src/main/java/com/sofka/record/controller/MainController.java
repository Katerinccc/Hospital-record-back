package com.sofka.record.controller;

import com.sofka.record.controller.dto.ResponseExceptionDTO;
import com.sofka.record.utility.BusinessException;
import com.sofka.record.utility.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
public class MainController {

    protected ResponseExceptionDTO getErrorMessageInternal(Exception exception) {
        Response response = new Response();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();

        return new ResponseExceptionDTO(response,httpStatus);
    }

    protected ResponseExceptionDTO getErrorMessageForResponse(DataAccessException dataException) {
        Response response = new Response();
        HttpStatus httpStatus;
        response.error = true;

        if(dataException.getRootCause() instanceof SQLException sqlEx) {
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062 -> response.message = "The data is registered already.";
                case 1452 -> response.message = "The indicated speciality/patient does not exist.";
                case 1451 -> response.message = "Deletion denied";
                default -> {
                    response.message = dataException.getMessage();
                    response.data = dataException.getCause();
                }
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = dataException.getMessage();
            response.data = dataException.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseExceptionDTO(response,httpStatus);
    }

    protected ResponseExceptionDTO getErrorBusinessException(BusinessException businessException) {
        Response response = new Response();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        response.error = true;
        response.message = businessException.getMessage();
        response.data = businessException.getCause();

        return new ResponseExceptionDTO(response,httpStatus);
    }
}
