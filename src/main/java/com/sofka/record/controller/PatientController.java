package com.sofka.record.controller;

import com.sofka.record.controller.dto.PatientDTO;
import com.sofka.record.controller.dto.ResponseExceptionDTO;
import com.sofka.record.domain.Patient;
import com.sofka.record.service.PatientService;
import com.sofka.record.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/patient")
@CrossOrigin(origins = "*")
public class PatientController{

        @Autowired
        private PatientService patientService;

        @Autowired
        private MainController mainController;

        private Response response = new Response();
        private HttpStatus httpStatus = HttpStatus.OK;

        @GetMapping(path = "/validate/{idSpeciality}/{identification}")
        public ResponseEntity<Response> getExistentPatient(@PathVariable(value = "idSpeciality") Integer idSpeciality,
                                                        @PathVariable(value = "identification") Long identification){
                response.restart();
                try {
                        Integer idPatient = patientService.getPatient(idSpeciality,
                                identification);
                        if (idPatient == null) {
                                response.data = null;
                                response.message = "Patient not found.";
                                httpStatus = HttpStatus.NOT_FOUND;
                        } else {
                                response.data = idPatient;
                                response.message = "Patient found";
                                httpStatus = HttpStatus.OK;
                        }
                }catch (Exception exception) {
                        ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }
                return new ResponseEntity(response, httpStatus);
        }

        @PostMapping
        public ResponseEntity<Response> createPatient(@RequestBody PatientDTO patientDTO){
                response.restart();
                try {
                        Patient patientRequest = new Patient();
                        patientRequest.setSpeciality(patientDTO.getSpeciality());
                        patientRequest.setName(patientDTO.getName());
                        patientRequest.setIdentification(patientDTO.getIdentification());
                        patientRequest.setAge(patientDTO.getAge());
                        Patient patient = patientService.createPatient(patientRequest);

                        response.data = new PatientDTO(patient.getIdPatient(),
                                patient.getSpeciality(),
                                patient.getName(),
                                patient.getIdentification(),
                                patient.getAge(),
                                patient.getNumberAppointments());
                        httpStatus = HttpStatus.OK;
                }catch (DataAccessException dataAccessException){
                        ResponseExceptionDTO responseExceptionDTO =
                                mainController.getErrorMessageForResponse(dataAccessException);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }catch (Exception exception) {
                        ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }
                return new ResponseEntity(response, httpStatus);
        }

        @PostMapping(path = "/appointment")
        public ResponseEntity<Response> addNewAppointment(@RequestBody Integer idPatient){
                response.restart();
                try {
                        Integer idAppointment = patientService.addNewAppointment(idPatient);

                        if (idAppointment == null) {
                                response.data = false;
                                response.message = "Patient not found.";
                                httpStatus = HttpStatus.NOT_FOUND;
                        } else {
                                response.data = idAppointment;
                                response.message = "OK";
                                httpStatus = HttpStatus.OK;
                        }
                }catch (DataAccessException dataAccessException){
                        ResponseExceptionDTO responseExceptionDTO =
                                mainController.getErrorMessageForResponse(dataAccessException);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }catch (Exception exception) {
                        ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }
                return new ResponseEntity(response, httpStatus);
        }

        @DeleteMapping(path="/{id}")
        public ResponseEntity<Response> deletePatient(@PathVariable(value = "id") Integer id) {
                response.restart();
                try {
                        response.data = patientService.deletePatient(id);
                        if (response.data == null) {
                                response.data = false;
                                response.message = "Patient not found.";
                                httpStatus = HttpStatus.NOT_FOUND;
                        } else {
                                response.data = true;
                                response.message = "Patient deleted successfully.";
                                httpStatus = HttpStatus.OK;
                        }
                } catch (DataAccessException dataAccessException) {
                        ResponseExceptionDTO responseExceptionDTO =
                                mainController.getErrorMessageForResponse(dataAccessException);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }catch (Exception exception) {
                        ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
                        response = responseExceptionDTO.getResponse();
                        httpStatus = responseExceptionDTO.getHttpStatus();
                }
                return new ResponseEntity(response, httpStatus);
        }

}
