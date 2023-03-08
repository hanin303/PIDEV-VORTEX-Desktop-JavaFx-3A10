package entity;

public class Trajet {
    
    private int id;
    private String temps_parcours;
    private String pts_depart;
    private String pts_arrivee;
    private String fromto;

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public Trajet(String fromto) {
        this.fromto = fromto;
    }
    

    public Trajet() {
    }

    public Trajet(String temps_parcours, String pts_depart, String pts_arrivee) {
        this.temps_parcours = temps_parcours;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
    }
    

    public Trajet(int id, String temps_parcours, String pts_depart, String pts_arrivee) {
        this.id = id;
        this.temps_parcours = temps_parcours;
        this.pts_depart = pts_depart;
        this.pts_arrivee = pts_arrivee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemps_parcours() {
        return temps_parcours;
    }

    public void setTemps_parcours(String temps_parcours) {
        this.temps_parcours = temps_parcours;
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

    @Override
    public String toString() {
        return "Trajet [id=" + id + ", temps_parcours=" + temps_parcours + ", pts_depart=" + pts_depart
                + ", pts_arrivee=" + pts_arrivee + "]";
    }
    
}


