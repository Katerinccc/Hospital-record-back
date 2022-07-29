package com.sofka.record.service.interfaces;

import com.sofka.record.domain.Patient;

public interface IPatientInterface {

    Patient createPatient(Patient patient);

    Integer getPatient(Integer idSpeciality, Long identification);

    Integer addNewAppointment(Integer idPatient);

    Patient deletePatient(Integer idPatient);


}
