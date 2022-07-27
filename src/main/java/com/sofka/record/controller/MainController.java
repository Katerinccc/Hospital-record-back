package com.sofka.record.controller;

import com.sofka.record.utility.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import java.sql.SQLException;

public class MainController {

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    protected void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    
    protected void getErrorMessageForResponse(DataAccessException dataException) {
        response.error = true;
        if(dataException.getRootCause() instanceof SQLException sqlEx) {
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062 -> response.message = "The data is registered already.";
                case 1452 -> response.message = "The indicated user does not exist.";
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
    }

}
