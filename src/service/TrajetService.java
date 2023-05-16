package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexionbd.utils.DataSource;
import entity.Trajet;
public class TrajetService implements IService<Trajet> {
    private Connection conn;
    public TrajetService() {
        conn=DataSource.getInstance().getCnx();
    }
    
    @Override
    public void insert(Trajet t){
        String requete= "INSERT INTO trajet (temps_parcours,pts_depart,pts_arrive) VALUES (?,?,?)";
        try {
            PreparedStatement ste=conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ste.setString(1, t.getTemps_parcours());
            ste.setString(2, t.getPts_depart());
            ste.setString(3, t.getPts_arrivee());
            ste.executeUpdate();

             try (ResultSet generatedKeys = ste.getGeneratedKeys()) {
                 if (generatedKeys.next()) {
                     int id = generatedKeys.getInt(1);
                     System.out.println("Auto-generated ID: " + id);
                     t.setId(id);
                 } else {
                     throw new SQLException("Inserting row failed, no ID obtained.");
                 }
             }
        } catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }    
    }
    @Override
    public void delete(int id) {
            String requete="delete from trajet where id= "+id;
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @Override
    public void update(List<Object> list,int id) {
        String requete="update trajet set temps_parcours=?,pts_depart=?,pts_arrive=? where id="+ id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, (String) list.get(0));
            pst.setString(2, (String) list.get(1));
            pst.setString(3, (String) list.get(2));
            // pst.setInt(4, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Trajet> readAll(){
        List<Trajet> list=new ArrayList<>();
        String requete="select * from trajet";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                list.add(new Trajet(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Trajet readByID(int id) {
        String requete = "select * from trajet where id = ?";
        Trajet trajet =  null;
        try{
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                trajet = new Trajet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));

            }

        }catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trajet;
    }
    
}
