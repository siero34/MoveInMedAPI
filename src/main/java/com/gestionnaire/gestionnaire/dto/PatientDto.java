package com.gestionnaire.gestionnaire.dto;

import com.gestionnaire.gestionnaire.model.Pro;

import java.util.Set;

public class PatientDto {

    private int id;

    private String nom;

    private String prenom;

    private Pro pro;

    private Set<Pro> pros;

    public PatientDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }

    public Set<Pro> getPros() {
        return pros;
    }

    public void setPros(Set<Pro> pros) {
        this.pros = pros;
    }
}
