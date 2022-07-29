package com.sofka.record.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpecialityDTO {

    private Integer idSpeciality;
    private String name;

    private String physician;

    private List<PatientDTO> patients = new ArrayList<>();

    public SpecialityDTO(Integer idSpeciality, String name, String physician, List<PatientDTO> patients) {
        this.idSpeciality = idSpeciality;
        this.name = name;
        this.physician = physician;
        this.patients = patients;
    }

    public SpecialityDTO(Integer idSpeciality, String name, String physician) {
        this.idSpeciality = idSpeciality;
        this.name = name;
        this.physician = physician;
    }

}
