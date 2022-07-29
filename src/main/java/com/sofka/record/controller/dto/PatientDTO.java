package com.sofka.record.controller.dto;

import com.sofka.record.domain.Speciality;
import lombok.Data;

@Data
public class PatientDTO {

    private Integer idPatient;
    private Speciality speciality;
    private String name;
    private Long identification;
    private Integer age;
    private Long numberAppointments;

    public PatientDTO(Integer idPatient,
                      Speciality speciality,
                      String name,
                      Long identification,
                      Integer age,
                      Long numberAppointments)
    {
        this.idPatient = idPatient;
        this.speciality = speciality;
        this.name = name;
        this.identification = identification;
        this.age = age;
        this.numberAppointments = numberAppointments;
    }
}
