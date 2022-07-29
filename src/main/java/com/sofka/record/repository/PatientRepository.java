package com.sofka.record.repository;

import com.sofka.record.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT id_patient FROM patient " +
                    "WHERE id_speciality = :idSpeciality " +
                    "AND identification = :identification" ,
                     nativeQuery = true)
    Integer validatePatientExist(@Param(value = "idSpeciality") Integer idSpeciality,
                           @Param(value = "identification") Long identification);


}