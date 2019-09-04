package com.gestionnaire.gestionnaire.dao;

import com.gestionnaire.gestionnaire.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

    Patient findById(int id);
    List<Patient> findByDateDeNaissance(LocalDate date);
    List<Patient> findByNomLike(String nom);
    List<Patient> findByPrenomLike(String prenom);
}
