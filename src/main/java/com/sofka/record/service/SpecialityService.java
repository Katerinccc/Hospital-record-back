package com.sofka.record.service;

import com.sofka.record.domain.Speciality;
import com.sofka.record.repository.SpecialityRepository;
import com.sofka.record.service.interfaces.ISpecialityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SpecialityService implements ISpecialityInterface {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Speciality> getSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    @Transactional
    public Speciality createSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    @Transactional
    public Speciality updateSpeciality(int idSpeciality, Speciality speciality) {
        speciality.setIdSpeciality(idSpeciality);
        return specialityRepository.save(speciality);
    }

    @Override
    @Transactional
    public void deleteSpeciality(int idSpeciality) {
        var speciality = specialityRepository.findById(idSpeciality);
        speciality.ifPresent(value -> specialityRepository.delete(value));
    }
}
