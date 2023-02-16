/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author hanin
 */
public class Reservation {
    public enum Status {
            confirme , en_attente , annule
    }
    private int id_reservation ;
    private Status status;
    private Date date_reservation ;
    private Double heure_depart ;
    private Double heure_arrive ;
    private int id_user;
    private int id_moy;
    private int id_it;
    private String type_ticket;
    
    public Reservation() { 
        
    }

    public Reservation(int id_reservation, Status status, Date date_reservation, Double heure_depart, Double heure_arrive, int id_user, int id_moy, int id_it, String type_ticket) {
        this.id_reservation = id_reservation;
        this.status = status;
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.id_user = id_user;
        this.id_moy = id_moy;
        this.id_it = id_it;
        this.type_ticket = type_ticket;
    }

    public Reservation(Status status, Date date_reservation, Double heure_depart, Double heure_arrive, int id_user, int id_moy, int id_it, String type_ticket) {
        this.status = status;
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.id_user = id_user;
        this.id_moy = id_moy;
        this.id_it = id_it;
        this.type_ticket = type_ticket;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public Status getStatus() {
        return status;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public Double getHeure_depart() {
        return heure_depart;
    }

    public Double getHeure_arrive() {
        return heure_arrive;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_moy() {
        return id_moy;
    }

    public int getId_it() {
        return id_it;
    }

    public String getType_ticket() {
        return type_ticket;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setHeure_depart(Double heure_depart) {
        this.heure_depart = heure_depart;
    }

    public void setHeure_arrive(Double heure_arrive) {
        this.heure_arrive = heure_arrive;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_moy(int id_moy) {
        this.id_moy = id_moy;
    }

    public void setId_it(int id_it) {
        this.id_it = id_it;
    }

    public void setType_ticket(String type_ticket) {
        this.type_ticket = type_ticket;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", status=" + status + ", date_reservation=" + date_reservation + ", heure_depart=" + heure_depart + ", heure_arrive=" + heure_arrive + ", id_user=" + id_user + ", id_moy=" + id_moy + ", id_it=" + id_it + ", type_ticket=" + type_ticket + '}';
    }

    
     
}

