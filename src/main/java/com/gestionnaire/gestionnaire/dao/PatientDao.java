package com.gestionnaire.gestionnaire.dao;

import com.gestionnaire.gestionnaire.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

    Patient findById(int id);
}
