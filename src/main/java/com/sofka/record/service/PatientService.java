package com.sofka.record.service;

import com.sofka.record.domain.Appointment;
import com.sofka.record.domain.Patient;
import com.sofka.record.repository.AppointmentRepository;
import com.sofka.record.repository.PatientRepository;
import com.sofka.record.service.interfaces.IPatientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
public class PatientService implements IPatientInterface {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public Integer getPatient(Integer idSpeciality, Long identification) {
        return patientRepository.validatePatientExist(idSpeciality, identification);
    }

    @Override
    @Transactional
    public Patient createPatient(Patient patient) {
        patient.setNumberAppointments(1L);
        Patient newPatient = patientRepository.save(patient);
        addNewAppointment(newPatient.getIdPatient());
        return newPatient;
    }

    @Override
    @Transactional
    public Integer addNewAppointment(Integer idPatient) {

        var optionalPatient = patientRepository.findById(idPatient);

        if (optionalPatient.isEmpty()) {
            return null;
        }

        Patient patient = optionalPatient.get();
        patient.increaseAppointmentNumber();
        Appointment appointment = new Appointment(patient,Instant.now());
        appointmentRepository.save(appointment);
        return appointment.getIdAppointment();
    }

    @Override
    @Transactional
    public Patient deletePatient(Integer idPatient) {
        var patient = patientRepository.findById(idPatient);
        if (patient.isPresent()){
            patientRepository.delete(patient.get());
            return patient.get();
        }else {
            return null;
        }
    }
}
