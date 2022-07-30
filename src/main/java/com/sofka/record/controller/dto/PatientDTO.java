package com.sofka.record.controller.dto;

import lombok.Data;

@Data
public class PatientDTO {

    private Integer idPatient;
    private Integer idSpeciality;
    private String name;
    private Long identification;
    private Integer age;
    private Long numberAppointments;

    public PatientDTO(Integer idPatient,
                      Integer idSpeciality,
                      String name,
                      Long identification,
                      Integer age,
                      Long numberAppointments)
    {
        this.idPatient = idPatient;
        this.idSpeciality = idSpeciality;
        this.name = name;
        this.identification = identification;
        this.age = age;
        this.numberAppointments = numberAppointments;
    }

    public PatientDTO() {
    }
}
