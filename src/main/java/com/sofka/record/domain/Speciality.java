package com.sofka.record.domain;

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
    private List<Patient> patients = new ArrayList<>();

}