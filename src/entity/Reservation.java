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
 * @author hanin
 */
public class Reservation {
//    public enum Status {
//            confirme , en_attente , annule
//    }
    private int id_reservation ;
    private String status;
    private LocalDate date_reservation ;
    private String heure_depart ;
    private String heure_arrive ;
    private int id_moy;
    private int id_user;
    private Iteneraire iteneraire ;
    private String type_ticket;
    
    public Reservation() { 
        
    }

    public Reservation(int id_reservation, String status, LocalDate date_reservation, String heure_depart, String heure_arrive, int id_user , int id_moy , Iteneraire iteneraire, String type_ticket) {
        this.id_reservation = id_reservation;
        this.status = status;
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.id_user = id_user;
        this.id_moy = id_moy;
        this.iteneraire = iteneraire;
        this.type_ticket = type_ticket;
    }

    public Reservation(String status, LocalDate date_reservation, String heure_depart, String heure_arrive, int id_user, int id_moy, Iteneraire iteneraire, String type_ticket) {
        this.status = status;
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.id_user = id_user;
        this.id_moy = id_moy;
        this.iteneraire = iteneraire;
        this.type_ticket = type_ticket;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }


    public String getHeure_depart() {
        return heure_depart;
    }

    public String getHeure_arrive() {
        return heure_arrive;
    }

   
    public int getId_user() {
        return id_user;
    }


    public int getId_moy() {
        return id_moy;
    }

 

    public Iteneraire getIteneraire() {
        return iteneraire;
    }

    public String getType_ticket() {
        return type_ticket;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate_reservation(LocalDate date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public void setHeure_arrive(String heure_arrive) {
        this.heure_arrive = heure_arrive;
    }


    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

  

    public void setId_moy(int id_moy) {
        this.id_moy = id_moy;
    }

    public void setIteneraire(Iteneraire iteneraire) {
        this.iteneraire = iteneraire;
    }

    public void setType_ticket(String type_ticket) {
        this.type_ticket = type_ticket;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", status=" + status + ", date_reservation=" + date_reservation + ", heure_depart=" + heure_depart + ", heure_arrive=" + heure_arrive + ", id_moy=" + id_moy + ", id_user=" + id_user + ", iteneraire=" + iteneraire.getId() + ", type_ticket=" + type_ticket + '}';
    }

    



 

     
}

