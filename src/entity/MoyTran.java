/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus
 */
public class MoyTran {
    
    
    private int id;
    private int matricule;
    private int capacite;
    private String type_vehicule;
    private String marque;
    private String etat;
    private Ligne ligne;
    private int id_ligne;

    public MoyTran(int id, int matricule, int capacite, String type_vehicule, String marque, String etat,int id_ligne, Ligne ligne ) {
        this.id = id;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.ligne = ligne;
        this.id_ligne = id_ligne;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public MoyTran() {
    }

    public MoyTran(int id, int matricule, int capacite, String type_vehicule, String marque, String etat) {
        this.id = id;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
    }

    public MoyTran(int matricule, int capacite, String type_vehicule, String marque, String etat) {
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
    }

    public MoyTran(int id, int matricule, int capacite, String type_vehicule, String marque, String etat, Ligne ligne) {
        this.id = id;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.ligne = ligne;
    }

    public MoyTran(int matricule, int capacite, String type_vehicule, String marque, String etat, Ligne ligne) {
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.ligne = ligne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getType_vehicule() {
        return type_vehicule;
    }

    public void setType_vehicule(String type_vehicule) {
        this.type_vehicule = type_vehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "MoyTran{" + "id=" + id + ", matricule=" + matricule + ", capacite=" + capacite + ", type_vehicule=" + type_vehicule + ", marque=" + marque + ", etat=" + etat + ", ligne=" + ligne.getId_ligne() + '}';
    }

   
    
}
