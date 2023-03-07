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
public class Station {
    private int id_station;
    private String lang_alt;
    private int id_moyen_transport;

    public Station(int id_station, String lang_alt, int id_moyen_transport) {
        this.id_station = id_station;
        this.lang_alt = lang_alt;
        this.id_moyen_transport = id_moyen_transport;
    }

    public Station(String lang_alt, int id_moyen_transport) {
        this.lang_alt = lang_alt;
        this.id_moyen_transport = id_moyen_transport;
    }

    public int getId_station() {
        return id_station;
    }

    public String getLang_alt() {
        return lang_alt;
    }

    public int getId_moyen_transport() {
        return id_moyen_transport;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    public void setLang_alt(String lang_alt) {
        this.lang_alt = lang_alt;
    }

    public void setId_moyen_transport(int id_moyen_transport) {
        this.id_moyen_transport = id_moyen_transport;
    }
  
     @Override
    public String toString() {
        return "Station{" + "id_station=" + id_station + ", lang_alt=" + lang_alt + ", id_moyen_ttranspor=" + id_moyen_transport +  '}';
    }
}
