package com.sofka.record.service.interfaces;

import com.sofka.record.domain.Speciality;
import java.util.List;

public interface ISpecialityInterface {

    List<Speciality> getSpecialities();
    Speciality createSpeciality(Speciality speciality);
    Speciality updateSpeciality(Integer idSpeciality, Speciality speciality);
    Speciality deleteSpeciality(Integer idSpeciality);

}
