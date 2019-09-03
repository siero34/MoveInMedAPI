package com.gestionnaire.gestionnaire.dao;

import com.gestionnaire.gestionnaire.model.Domaine;
import com.gestionnaire.gestionnaire.model.Pro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProDao extends JpaRepository<Pro, Integer> {
    Pro findById(int id);
    List<Pro> findByDomaine(Domaine domaine);
    List<Pro> findByNomLike(String nom);
    List<Pro> findByPrenomLike(String prenom);
}
