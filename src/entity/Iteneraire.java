package entity;

public class Iteneraire {
    private int id;
    private String pts_depart;
    private String pts_arrivee;
    private Trajet trajet;
    private int trajet_id;
    private String fromto;

    public String getFromto() {
        return fromto;
    }

    public Iteneraire(int id, String pts_depart, String pts_arrivee, String fromto) {
        this.id = id;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        this.fromto = fromto;
        System.out.println("used");
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public Iteneraire(String pts_depart, String pts_arrivee, Trajet trajet, String fromto) {
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        this.trajet = trajet;
        this.fromto = fromto;
    }
    
    
    public Iteneraire( int id ,String pts_depart, String pts_arrivee, Trajet trajet) {
        this.id = id;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        this.trajet = trajet;
       
    }
    public Iteneraire( int id ,String pts_depart, String pts_arrivee, int id_trajet) {
        this.id = id;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        this.trajet_id = id_trajet; 
        
        
       
    }
    public Iteneraire(String pts_depart, String pts_arrivee, Trajet trajet) {
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        this.trajet = trajet;
    }
    public Iteneraire(int id, String pts_depart, String pts_arrivee) {
        this.id = id;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
        
       
    }
    
    
    public Iteneraire(String pts_depart, String pts_arrivee) {
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
    }

    public Iteneraire() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPts_depart() {
        return pts_depart;
    }
    public void setPts_depart(String pts_depart) {
        this.pts_depart = pts_depart;
    }
    public String getPts_arrivee() {
        return pts_arrivee;
    }
    public void setPts_arrivee(String pts_arrivee) {
        this.pts_arrivee = pts_arrivee;
    }

    public int getTrajet_id() {
        return trajet_id;
    }

    public void setTrajet_id(int trajet_id) {
        this.trajet_id = trajet_id;
    }
   
    @Override
    public String toString() {
        return "Iteneraire [id=" + id + ", pts_depart=" + pts_depart + ", pts_arrivee=" + pts_arrivee + ", trajet="
                + fromto + "]";
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
    

}
