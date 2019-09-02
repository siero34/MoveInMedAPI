package com.gestionnaire.gestionnaire.model;

import com.fasterxml.jackson.annotation.*;
import com.gestionnaire.gestionnaire.views.View;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pro")
public class Pro implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name = "domaine")
    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @JsonBackReference
    @ManyToMany(mappedBy = "pros")
    private Set<Patient> patients = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    public Pro() {
    }

    public Pro(int id, String nom, String prenom, Domaine domaine, Set<Patient> patients, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
        this.patients = patients;
        this.adresse = adresse;
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

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
        patient.getPros().add(this);
    }

    public void removePatient(Patient patient){
        this.patients.remove(patient);
        patient.getPros().remove(this);
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
