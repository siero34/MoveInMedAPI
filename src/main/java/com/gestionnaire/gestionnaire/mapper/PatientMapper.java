package com.gestionnaire.gestionnaire.mapper;

import com.gestionnaire.gestionnaire.dto.PatientDto;
import com.gestionnaire.gestionnaire.model.Patient;

public class PatientMapper {
    public static PatientDto toPatientDto(Patient patient){
        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setNom(patient.getNom());
        patientDto.setPrenom(patient.getPrenom());
        patientDto.setPro(patient.getPro());
//        patientDto.setPros(patient.getPros());

        return patientDto;
    }
}
