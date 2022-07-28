package com.sofka.record.controller;

import com.sofka.record.service.PatientService;
import com.sofka.record.utility.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/patient")
@CrossOrigin(origins = "http://127.0.0.1:5500",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT,
                RequestMethod.PATCH
        })
public class PatientController{

        @Autowired
        private PatientService patientService;

        @Autowired
        private MainController mainController;

        @Autowired
        private ModelMapper modelMapper;

        private Response response = new Response();
        private HttpStatus httpStatus = HttpStatus.OK;

}
