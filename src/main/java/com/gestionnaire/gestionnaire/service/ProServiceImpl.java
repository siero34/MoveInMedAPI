package com.gestionnaire.gestionnaire.service;

import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Domaine;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProServiceImpl implements ProService {

    @Autowired
    private ProDao proDao;

    @Override
    public List<Pro> findAll() {
        return proDao.findAll();
    }

    @Override
    public Pro findById(int id) {
        return proDao.findById(id);
    }

    @Override
    public Set<Patient> getPatients(int id) {
        return proDao.findById(id).getPatients();
    }

    @Override
    public Pro save(Pro pro) {
        return proDao.save(pro);
    }

    @Override
    public List<Pro> sortByNom(String nom) {
        return null;
    }

    @Override
    public List<Pro> sortByPrenom(String prenom) {
        return null;
    }

    @Override
    public List<Pro> findByNom(String nom) {
        return null;
    }

    @Override
    public List<Pro> findByPrenom(String Prenom) {
        return null;
    }

    @Override
    public List<Pro> findByDomaine(Domaine domaine) {
        return null;
    }

    @Override
    public Pro update(Pro pro) {
        return null;
    }

    @Override
    public void delete(Pro pro) {

    }
}
