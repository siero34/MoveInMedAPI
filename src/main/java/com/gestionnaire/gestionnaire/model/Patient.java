package com.gestionnaire.gestionnaire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestionnaire.gestionnaire.serializer.CustomPatientSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="patient")
//@JsonSerialize(using = CustomPatientSerializer.class)
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @OneToOne
    @JoinColumn(name = "pro_id")
    private Pro pro;

    @JsonManagedReference
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "relation",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "pro_id"))
    private Set<Pro> pros = new HashSet<>();


    public Patient() {
    }

    public Patient(int id, String nom, String prenom, Pro pro) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pro = pro;
    }

    public Patient(int id, String nom, String prenom, Pro pro, Set<Pro> pros) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pro = pro;
        this.pros = pros;
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

    public void addPro(Pro pro){
        this.pros.add(pro);
        pro.getPatients().add(this);
    }

    public void removePro(Pro pro){
        this.pros.remove(pro);
        pro.getPatients().remove(this);
    }
}
