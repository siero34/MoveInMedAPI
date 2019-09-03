package com.gestionnaire.gestionnaire.service;

import com.gestionnaire.gestionnaire.model.Domaine;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ProService {
    public abstract List<Pro> findAll();
    public abstract Pro findById(int id);
    public abstract Set<Patient> getPatients(int id);
    public abstract Pro save(Pro pro);
    public abstract List<Pro> sortByNom(String nom);
    public abstract List<Pro> sortByPrenom(String prenom);
    public abstract List<Pro> findByNom(String nom);
    public abstract List<Pro> findByPrenom(String Prenom);
    public abstract List<Pro> findByDomaine(Domaine domaine);
    public abstract Pro update(Pro pro);
    public abstract void delete(Pro pro);
    public abstract List<Pro> search(String nom, String prenom, Domaine domaine);
}
