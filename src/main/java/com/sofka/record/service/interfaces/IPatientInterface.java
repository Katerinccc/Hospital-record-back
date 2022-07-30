package com.sofka.record.service.interfaces;

import com.sofka.record.domain.Patient;
import com.sofka.record.utility.BusinessException;
import java.util.List;

public interface IPatientInterface {

    Patient createPatient(Patient patient) throws BusinessException;

    Integer getPatient(Integer idSpeciality, Long identification);

    Integer addNewAppointment(Integer idPatient);

    Patient deletePatient(Integer idPatient);

    List<Patient> getPatientsList();


}
