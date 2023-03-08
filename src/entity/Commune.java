/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author User
 */

public class Commune {
    private int id_commune;
    private String nom_commune;
    private String long_alt;

    public Commune(int id_commune, String nom_commune, String long_alt) {
        this.id_commune = id_commune;
        this.nom_commune = nom_commune;
        this.long_alt = long_alt;
    }

    public Commune(String nom_commune, String long_alt) {
        this.nom_commune = nom_commune;
        this.long_alt = long_alt;
    }

    public int getId_commune() {
        return id_commune;
    }

    public String getNom_commune() {
        return nom_commune;
    }

    public String getLong_alt() {
        return long_alt;
    }

    public void setId_commune(int id_commune) {
        this.id_commune = id_commune;
    }

    public void setNom_commune(String nom_commune) {
        this.nom_commune = nom_commune;
    }

    public void setLong_alt(String long_alt) {
        this.long_alt = long_alt;
    }
  
    @Override
    public String toString() {
        return "Commune{" + "id_commune=" + id_commune + ", nom_commune=" + nom_commune + ", long_alt=" + long_alt + '}';
    }
}
