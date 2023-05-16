/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hanin
 */
public class Ticket {
//        public enum Status {
//            paye , non_paye
//    }
    private int id_t ;
    private String status;
    private String prix;
    private int id_reservation ;
    private Reservation reservation ;
   
    
    
     public Ticket() { 
        
    }
     
     

    public Ticket(int id_t, String status, String prix, Reservation reservation) {
        this.id_t = id_t;
        this.status = status;
        this.prix = prix;
        this.reservation = reservation;
    }

    public Ticket(String status, String prix, Reservation reservation) {
        this.status = status;
        this.prix = prix;
        this.reservation = reservation;
    }

    public int getId_t() {
        return id_t;
    }

    public String getStatus() {
        return status;
    }

    public Ticket(int id_t, String status, String prix, int id_reservation) {
        this.id_t = id_t;
        this.status = status;
        this.prix = prix;
        this.id_reservation = id_reservation;
    }

    public String getPrix() {
        return prix;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setId_t(int id_t) {
        this.id_t = id_t;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_t=" + id_t + ", status=" + status + ", prix=" + prix + ", reservation=" + getId_reservation()+ '}';
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    
}