/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author wassim
 */
public class Reclamation {
      
private
        int id_reclamation;
        String message_rec,objet,statut;
        LocalDate date_rec;
        
        public Reclamation() {
        }

    public Reclamation(int id_reclamation, String message_rec, String objet, String statut) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
    }

    public Reclamation(String message_rec, String objet, String statut, LocalDate date_rec) {
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
        this.date_rec = date_rec;
    }

    public Reclamation(String message_rec, String objet, String statut) {
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
    }
    
        
        public Reclamation(int id_reclamation, String objet, String message_rec, String statut, LocalDate date_rec) {
            
            this.id_reclamation = id_reclamation;
            this.objet = objet;
            this.message_rec = message_rec;
            this.statut = statut;
            this.date_rec = date_rec;
        }

    public Reclamation(int id_reclamation, String message_rec, String objet) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.objet = objet;
    }
        
        

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getObjet() {
        return objet;
    }

    public void setMessgae_rec(String messgae_rec) {
        this.message_rec = messgae_rec;
    }

    public String getMessgae_rec() {
        return message_rec;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return statut;
    }

    public LocalDate getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(LocalDate date_rec) {
        this.date_rec = date_rec;
    }

   @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", objet=" + objet + ", message_rec=" + message_rec + ", statut=" + statut + ", date_rec=" + date_rec +'}';
    }
    public void ajouter(Reclamation R) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
