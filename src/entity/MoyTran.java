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
    
    
    private int id_moy;
    private int matricule;
    private int num;
    private int capacite;
    private String type_vehicule;
    private String marque;
    private String etat;
    
    private int id_ligne;
    private Ligne ligne;
    private float note; 
    private int id_station;
    

    public MoyTran() {
    }

    public MoyTran(int id_moy, int matricule, int capacite, String type_vehicule, String marque, String etat, Ligne ligne) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.ligne = ligne;
    }

    public MoyTran(int id_moy) {
        this.id_moy = id_moy;
    }

    public MoyTran(int id_moy, int matricule, int num,int capacite, String type_vehicule, String marque, String etat,  int id_ligne, Ligne ligne) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.num = num;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
        this.ligne = ligne;
    }

    public MoyTran(int id_moy, int matricule, int capacite, String type_vehicule, String marque, String etat, int id_ligne, Ligne ligne) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
        this.ligne = ligne;
    }
    
///////plz
    public MoyTran( int matricule,int num, int capacite, String type_vehicule, String marque, String etat,  int id_ligne) {
        this.matricule = matricule;
         this.num = num;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
       
        this.id_ligne = id_ligne;
    }
//plz
    public MoyTran(int id_moy, int matricule, int num, int capacite, String type_vehicule, String marque, String etat, int id_ligne, int id_station) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.num = num;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
        this.id_station = id_station;
    }
    
    

    public MoyTran(int id_moy, int matricule, int capacite, String type_vehicule, String marque, String etat) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
    }

    public MoyTran(int id_moy, int matricule, int num, int capacite, String type_vehicule, String marque, String etat, int id_ligne) {
        this.id_moy = id_moy;
        this.matricule = matricule;
        this.num = num;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
    }

    
    
    
    public MoyTran(int matricule, int capacite, String type_vehicule, String marque, String etat) {
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    public MoyTran(int matricule, int num, int capacite, String type_vehicule, String marque, String etat, int id_ligne, int id_station) {
        this.matricule = matricule;
        this.num = num;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
        this.id_station = id_station;
    }
    
    
    
//     public MoyTran(int matricule, int capacite, String type_vehicule, String marque, String etat) {
//        this.matricule = matricule;
//        this.capacite = capacite;
//        this.type_vehicule = type_vehicule;
//        this.marque = marque;
//        this.etat = etat;
//    }

    public MoyTran(int matricule, int capacite, String type_vehicule, String marque, String etat, int id_ligne) {
        this.matricule = matricule;
        this.capacite = capacite;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat = etat;
        this.id_ligne = id_ligne;
    }

    public MoyTran(int id_moy, Ligne ligne) {
        this.id_moy = id_moy;
        this.ligne = ligne;
    }

    public MoyTran(int id_moy, int id_ligne) {
        this.id_moy = id_moy;
        this.id_ligne = id_ligne;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    

    

    public int getId_moy() {
        return id_moy;
    }

    public void setId_moy(int id_moy) {
        this.id_moy = id_moy;
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

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MoyTran{" + "id_moy=" + id_moy + ", matricule=" + matricule + ", num=" + num + ", capacite=" + capacite + ", type_vehicule=" + type_vehicule + ", marque=" + marque + ", etat=" + etat + ", id_ligne=" + id_ligne + '}';
    }

    
    

   
    
}
