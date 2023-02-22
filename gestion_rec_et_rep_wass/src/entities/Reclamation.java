/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Reclamation {

    
    
    private 
            int id_reclamation;
            String message_rec,type,statut;

    public Reclamation() {
    }

    public Reclamation(int id_reclamation, String message_rec, String type , String statut ) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.type = type;
        this.statut = statut;
        
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getMessage_rec() {
        return message_rec;
    }

    public void setMessage_rec(String message_rec) {
        this.message_rec = message_rec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", message_rec=" + message_rec + ", type=" + type + ", statut=" + statut + '}';
    }

    public void ajouter(Reclamation R) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}