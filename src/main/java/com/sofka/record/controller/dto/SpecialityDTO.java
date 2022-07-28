package com.sofka.record.controller.dto;

import com.sofka.record.domain.Patient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpecialityDTO {

    private Integer idSpeciality;
    private String name;

    private String physician;

    private List<Patient> patients = new ArrayList<>();

    public SpecialityDTO(int idSpeciality, String name, String physician) {
        this.idSpeciality = idSpeciality;
        this.name = name;
        this.physician = physician;
    }
}
