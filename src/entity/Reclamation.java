package entity;

public class Reclamation {
    private int id;
    private String contenu;
    private String date;
    private int user_id;
    private String status;
    
    public Reclamation() {
    }
    
    public Reclamation(int id, String contenu, String date, String status) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.status = status;
    }

    public Reclamation(String contenu, String date, String status) {
        this.contenu = contenu;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reclamation [id=" + id + ", contenu=" + contenu + ", date=" + date + ", user_id=" + user_id
                + ", status=" + status + "]";
    }
    
}
