package com.gestionnaire.gestionnaire.dao;

import com.gestionnaire.gestionnaire.model.Pro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProDao extends JpaRepository<Pro, Integer> {
    Pro findById(int id);
}
