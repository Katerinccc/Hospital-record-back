package com.sofka.record.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sofka.record.utility.BusinessException;
import com.sofka.record.utility.Util;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_speciality", nullable = false)
    private Integer idSpeciality;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "physician", nullable = false, length = 45)
    private String physician;

    @OneToMany(mappedBy = "speciality")
    @JsonManagedReference
    private List<Patient> patients = new ArrayList<>();

    public Speciality() {

    }

    public Speciality(Integer idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public Speciality(String name, String physician) throws BusinessException{
        this.name = validateName(name);
        this.physician = validatePhysician(physician);
    }

    public Speciality(Integer idSpeciality, String name, String physician) throws BusinessException{
        this.idSpeciality = idSpeciality;
        this.name = validateName(name);
        this.physician = validatePhysician(physician);
    }

    private String validateName(String name) throws BusinessException{
        if(Util.validateLength(name,5,100)){
            return name;
        }
        throw new BusinessException("Invalid speciality name.");
    }

    private String validatePhysician(String physician) throws BusinessException{
        if(Util.validateLength(physician,10,45)){
            return physician;
        }
        throw new BusinessException("Invalid physician name.");
    }
}