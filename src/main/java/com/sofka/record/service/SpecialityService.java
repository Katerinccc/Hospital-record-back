package com.sofka.record.service;

import com.sofka.record.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

}
