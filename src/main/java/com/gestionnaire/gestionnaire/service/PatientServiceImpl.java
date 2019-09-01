package com.gestionnaire.gestionnaire.service;

import com.gestionnaire.gestionnaire.dao.PatientDao;
import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientDao patientDao;

    @Override
    public Set<Pro> getPros(int id) {
        Set<Pro> pros = patientDao.findById(id).getPros();
        return pros;
    }

    @Override
    public List<Patient> findAll() {
        return patientDao.findAll();
    }

    @Override
    public Patient findById(int id) {
        return patientDao.findById(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientDao.save(patient);
    }

    @Override
    public List<Patient> sortByNom(String nom) {
        return null;
    }

    @Override
    public List<Patient> sortByPrenom(String prenom) {
        return null;
    }

    @Override
    public List<Patient> findByNom(String nom) {
        return null;
    }

    @Override
    public List<Patient> findByPrenom(String Prenom) {
        return null;
    }

    @Override
    public List<Patient> findByDateDeNaissance(Date date) {
        return null;
    }

    @Override
    public Patient update(Patient patient) {
        return null;
    }

    @Override
    public void delete(Patient patient) {

    }
}
