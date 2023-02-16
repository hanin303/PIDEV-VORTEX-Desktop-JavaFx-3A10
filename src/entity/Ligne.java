/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus
 */
public class Ligne {
    
     int id_ligne;
    String nom_ligne;
    String type_ligne;

    public Ligne() {
    }

    public Ligne(int id_ligne, String nom_ligne, String type_ligne) {
        this.id_ligne = id_ligne;
        this.nom_ligne = nom_ligne;
        this.type_ligne = type_ligne;
    }

    public Ligne(String nom_ligne, String type_ligne) {
        this.nom_ligne = nom_ligne;
        this.type_ligne = type_ligne;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public String getNom_ligne() {
        return nom_ligne;
    }

    public void setNom_ligne(String nom_ligne) {
        this.nom_ligne = nom_ligne;
    }

    public String getType_ligne() {
        return type_ligne;
    }

    public void setType_ligne(String type_ligne) {
        this.type_ligne = type_ligne;
    }

    @Override
    public String toString() {
        return "Ligne{" + "id_ligne=" + id_ligne + ", nom_ligne=" + nom_ligne + ", type_ligne=" + type_ligne + '}';
    }
    
    
    
    
    
    
}
