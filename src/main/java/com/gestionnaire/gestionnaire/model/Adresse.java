package com.gestionnaire.gestionnaire.model;

import javax.persistence.*;

@Entity
@Table(name="adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "zip_code")
    private int zipCode;


    public Adresse(int id, String adresse, String ville, int zipCode) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.zipCode = zipCode;
    }

    public Adresse() {
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
