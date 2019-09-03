package com.gestionnaire.gestionnaire.service;

import com.gestionnaire.gestionnaire.dao.PatientDao;
import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public Patient update(Patient patient) {
        return null;
    }

    @Override
    public void delete(Patient patient) {
        patientDao.deleteById(patient.getId());
    }

    @Override
    public void deleteById(int id){
        patientDao.deleteById(id);
    }

    @Override
    public List<Patient> search(String nom, String prenom, LocalDate date){

        // On initialise une liste vide
        List<Patient> patients = new ArrayList<>();

        // Si le paramètre "nom" est présent, on récupère tous les patients dont le nom correspondent
        if(nom != null)
            patients = patientDao.findByNomLike("%"+nom+"%");

        // Si le paramètre "prenom" est présent
        if(prenom != null){

            /* On vérifie tout d'abord si la liste a déjà été peuplée pour éviter d'effectuer l'opération retainAll
            avec une liste vide qui supprimerait tous les résultats précédents*/
            if(patients.isEmpty())
                patients = patientDao.findByPrenomLike("%"+prenom+"%");

            // Sinon on effectue une intersection des deux listes
            patients.retainAll(patientDao.findByPrenomLike("%"+prenom+"%"));
        }

        // Si le paramètre "date" est présent
        if(date != null){

            // On effectue la même vérification que pour le prenom
            if(patients.isEmpty())
                patients = patientDao.findByDateDeNaissance(date);

            // On effectue une nouvelle intersection, qui simulera une intersection des trois listes
            patients.retainAll(patientDao.findByDateDeNaissance(date));
        }

        return patients;
    }
}
