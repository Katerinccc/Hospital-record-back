package com.sofka.record.controller;

import com.sofka.record.controller.dto.PatientDTO;
import com.sofka.record.controller.dto.ResponseExceptionDTO;
import com.sofka.record.controller.dto.SpecialityDTO;
import com.sofka.record.controller.dto.SpecialityRequestDTO;
import com.sofka.record.domain.Patient;
import com.sofka.record.domain.Speciality;
import com.sofka.record.service.SpecialityService;
import com.sofka.record.utility.BusinessException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/speciality")
@CrossOrigin(origins = "*")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private MainController mainController;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping(path = "/list")
    public ResponseEntity<Response> getSpecialityList(){
        response.restart();
        try {
            List<Speciality> specialities = specialityService.getSpecialities();

            response.data = specialities
                    .stream()
                    .map(speciality ->
                            new SpecialityDTO(speciality.getIdSpeciality(),
                                    speciality.getName(), speciality.getPhysician(),
                                    getPatients(speciality.getPatients())))
                    .toList();
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        }

        return new ResponseEntity(response, httpStatus);
    }

    private List<PatientDTO> getPatients (List<Patient> patientList){
        List<PatientDTO> patientDTOList = new ArrayList();
        for (Patient patient : patientList) {
            patientDTOList.add(new PatientDTO(patient.getIdPatient(),patient.getSpeciality().getIdSpeciality(), patient.getName(),
                    patient.getIdentification(),patient.getAge(),patient.getNumberAppointments()));
        }

        return patientDTOList;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> getSpeciality(@PathVariable(value = "id") Integer id){
        response.restart();
        try {
            Speciality speciality = specialityService.getSpeciality(id);

            response.data = new SpecialityDTO(speciality.getIdSpeciality(),
                    speciality.getName(),
                    speciality.getPhysician());
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping
    public ResponseEntity<Response> createSpeciality(@RequestBody SpecialityRequestDTO specialityDto){
        response.restart();
        try {
            Speciality specialityRequest = new Speciality(specialityDto.getName(),specialityDto.getPhysician());

            Speciality speciality = specialityService.createSpeciality(specialityRequest);

            response.data = new SpecialityDTO(speciality.getIdSpeciality(),
                            speciality.getName(),
                            speciality.getPhysician());
            httpStatus = HttpStatus.CREATED;
        }catch (DataAccessException dataAccessException) {
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageForResponse(dataAccessException);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        } catch (BusinessException businessException){
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorBusinessException(businessException);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        } catch (Exception exception) {
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageInternal(exception);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Response> updateSpeciality(
            @PathVariable(value = "id") Integer id,
            @RequestBody SpecialityRequestDTO specialityDto
    ){
        response.restart();
        try {
            Speciality specialityRequest = new Speciality(id,specialityDto.getName(),
                    specialityDto.getPhysician());
            Speciality speciality = specialityService.updateSpeciality(id, specialityRequest);

            response.data = new SpecialityDTO(speciality.getIdSpeciality(),
                    specialityDto.getName(),
                    specialityRequest.getPhysician());
            httpStatus = HttpStatus.OK;
        }catch (DataAccessException dataAccessException){
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageForResponse(dataAccessException);
            response = responseExceptionDTO.getResponse();
            httpStatus = responseExceptionDTO.getHttpStatus();
        }catch (BusinessException businessException){
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorBusinessException(businessException);
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
    public ResponseEntity<Response> deleteSpeciality(@PathVariable(value = "id") Integer id) {
        response.restart();
        try {
            response.data = specialityService.deleteSpeciality(id);
            if (response.data == null) {
                response.data = false;
                response.message = "Speciality not found.";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.data = true;
                response.message = "Speciality deleted successfully.";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException dataAccessException) {
            ResponseExceptionDTO responseExceptionDTO = mainController.getErrorMessageForResponse(dataAccessException);
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
