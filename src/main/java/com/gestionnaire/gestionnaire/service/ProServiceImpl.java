package com.gestionnaire.gestionnaire.service;

import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Domaine;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return proDao.findByDomaine(domaine);
    }

    @Override
    public Pro update(Pro pro) {
        return null;
    }

    @Override
    public void delete(Pro pro) {

    }

    @Override
    public List<Pro> search(String nom, String prenom, Domaine domaine) {

        // On initialise une liste vide
        List<Pro> pros = new ArrayList<>();

        // Si le paramètre "nom" est présent, on récupère tous les patients dont le nom correspondent
        if (nom != null)
            pros = proDao.findByNomLike("%"+nom+"%");

        // Si le paramètre "prenom" est présent
        if (prenom != null) {

            /* On vérifie tout d'abord si la liste a déjà été peuplée pour éviter d'effectuer l'opération retainAll
            avec une liste vide qui supprimerait tous les résultats précédents*/
            if (pros.isEmpty())
                pros = proDao.findByPrenomLike("%"+prenom+"%");

            // Sinon on effectue une intersection des deux listes
            pros.retainAll(proDao.findByPrenomLike("%"+prenom+"%"));
        }

        // Si le paramètre "domaine" est présent
        if (domaine != null) {

            // On effectue la même vérification que pour le prenom
            if (pros.isEmpty())
                pros = proDao.findByDomaine(domaine);

            // On effectue une nouvelle intersection, qui simulera une intersection des trois listes
            pros.retainAll(proDao.findByDomaine(domaine));
        }

        return pros;
    }
}
