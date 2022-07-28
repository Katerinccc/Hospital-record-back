package com.sofka.record.controller.dto;

import lombok.Data;

@Data
public class SpecialityDto {

    private int id;
    private String name;

    private String physician;

    public SpecialityDto(int id, String name, String physician) {
        this.id = id;
        this.name = name;
        this.physician = physician;
    }
}
