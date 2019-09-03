package com.gestionnaire.gestionnaire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestionnaire.gestionnaire.serializer.CustomPatientDeserializer;
import com.gestionnaire.gestionnaire.serializer.CustomPatientSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="patient")
@JsonDeserialize(using = CustomPatientDeserializer.class)
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "relation",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "pro_id"))
    private Set<Pro> pros = new HashSet<>();

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "date_de_naissance")
    private Date dateDeNaissance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "email")
    private String email;

    public Patient() {
    }

    public Patient(int id, String nom, String prenom, Pro pro, Set<Pro> pros, Date dateDeNaissance, Adresse adresse, String numTel, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pro = pro;
        this.pros = pros;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
    }

    public Patient(String nom, String prenom, Pro pro, Set<Pro> pros, Date dateDeNaissance, Adresse adresse, String numTel, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pro = pro;
        this.pros = pros;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
    }

    public int getId() { return id; }

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

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
