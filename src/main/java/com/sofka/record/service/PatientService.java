package com.sofka.record.service;

import com.sofka.record.repository.AppointmentRepository;
import com.sofka.record.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

}
