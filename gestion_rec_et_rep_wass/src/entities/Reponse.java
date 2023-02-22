/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
public class Reponse {

    public static Object getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int id_reponse,id_reclamation ;
    String message_rep,date_rep;

    public Reponse() {
    }

    public Reponse(int id_reponse, int id_reclamation, String message_rep, String date_rep) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.message_rep = message_rep;
        this.date_rep = date_rep;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getMessage_rep() {
        return message_rep;
    }

    public void setMessage_rep(String message_rep) {
        this.message_rep = message_rep;
    }

    public String getDate_rep() {
        return date_rep;
    }

    public void setDate_rep(String date_rep) {
        this.date_rep = date_rep;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id_reponse=" + id_reponse + ", id_reclamation=" + id_reclamation + ", message_rep=" + message_rep + ", date_rep=" + date_rep + '}';
    }

 
    
    

}