package com.sofka.record.controller.dto;

import lombok.Data;

@Data
public class PatientResponseDTO extends PatientDTO {
    private String nameSpeciality;

    public PatientResponseDTO(Integer idPatient,
                              Integer idSpeciality,
                              String name,
                              Long identification,
                              Integer age,
                              Long numberAppointments,
                              String nameSpeciality) {
        super(idPatient, idSpeciality, name, identification, age, numberAppointments);
        this.nameSpeciality = nameSpeciality;
    }
}
