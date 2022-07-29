package com.sofka.record.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private Integer idPatient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_speciality", nullable = false)
    @JsonBackReference
    private Speciality speciality;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "identification", nullable = false)
    private Long identification;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "number_appointment", nullable = false)
    private Long numberAppointments;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Appointment> appointments = new ArrayList<>();

    public void increaseAppointmentNumber(){
        this.numberAppointments++;
    }


}