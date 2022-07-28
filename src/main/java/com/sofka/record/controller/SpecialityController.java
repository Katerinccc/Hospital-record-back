package com.sofka.record.controller;

import com.sofka.record.controller.dto.SpecialityDTO;
import com.sofka.record.domain.Speciality;
import com.sofka.record.service.SpecialityService;
import com.sofka.record.utility.Response;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/speciality")
@CrossOrigin(origins = "http://127.0.0.1:5500",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT,
                RequestMethod.PATCH
        })
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private MainController mainController;

    @Autowired
    private ModelMapper modelMapper;

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
                                    speciality.getName(), speciality.getPhysician()))
                    .toList();
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            mainController.getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping
    public ResponseEntity<Response> createSpeciality(@RequestBody SpecialityDTO specialityDto){
        response.restart();
        try {
            Speciality specialityRequest = new Speciality();
            specialityRequest.setName(specialityDto.getName());
            specialityRequest.setPhysician(specialityDto.getPhysician());
            Speciality speciality = specialityService.createSpeciality(specialityRequest);

            response.data = new SpecialityDTO(speciality.getIdSpeciality(),
                            specialityDto.getName(),
                            specialityRequest.getPhysician());
            httpStatus = HttpStatus.OK;
        }catch (DataAccessException dataAccessException){
            mainController.getErrorMessageForResponse(dataAccessException);
        }catch (Exception exception) {
            mainController.getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Response> updateSpeciality(
            @PathVariable(value = "id") Integer id,
            @RequestBody SpecialityDTO specialityDto
    ){
        response.restart();
        try {
            Speciality specialityRequest = new Speciality();
            specialityRequest.setIdSpeciality(id);
            specialityRequest.setName(specialityDto.getName());
            specialityRequest.setPhysician(specialityDto.getPhysician());
            Speciality speciality = specialityService.updateSpeciality(id, specialityRequest);

            response.data = new SpecialityDTO(speciality.getIdSpeciality(),
                    specialityDto.getName(),
                    specialityRequest.getPhysician());
            httpStatus = HttpStatus.OK;
        }catch (DataAccessException dataAccessException){
            mainController.getErrorMessageForResponse(dataAccessException);
        }catch (Exception exception) {
            mainController.getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response> deleteSpeciality(@PathVariable(value = "id") Integer id){
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
        } catch (DataAccessException exception) {
            mainController.getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            mainController.getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

}
