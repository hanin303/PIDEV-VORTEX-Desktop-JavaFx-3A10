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
        public enum Status {
            paye , non_paye
    }
    private int id_t ;
    private Status status;
    private float prix;
    private int id_reservation ;
    //private LocalDateTime dateTime;
    //private int numberOfTickets;
    
    
     public Ticket() { 
        
    }

    public Ticket(int id_t, Status status, float prix, int id_reservation) {
        this.id_t = id_t;
        this.status = status;
        this.prix = prix;
        this.id_reservation = id_reservation;
    }

    public Ticket(Status status, float prix, int id_reservation) {
        this.status = status;
        this.prix = prix;
        this.id_reservation = id_reservation;
    }

    public int getId_t() {
        return id_t;
    }
    
    public Status getStatus() {
        return status;
    }

    public float getPrix() {
        return prix;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_t(int id_t) {
        this.id_t = id_t;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_t=" + id_t + ", status=" + status + ", prix=" + prix + ", id_reservation=" + id_reservation + '}';
    }

    /*public boolean isValid() {
    return id_reservation != null && !id_reservation.isEmpty() && numberOfTickets > 0;
  }*/
    
}