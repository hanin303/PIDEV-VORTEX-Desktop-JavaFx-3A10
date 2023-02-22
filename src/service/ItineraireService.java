package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexionbd.utils.DataSource;
import entity.Iteneraire;
import entity.Trajet;
public class ItineraireService implements IService<Iteneraire>{
    private Connection conn;
    public ItineraireService() {
        conn=DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Iteneraire t){
        String requete= "insert into iteneraire (pts_depart,pts_arrive,id_trajet) values"
                + "('"+t.getPts_depart()+"','"+t.getPts_arrivee()+"','"+t.getTrajet().getId()+"')";
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ItineraireService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }    
    }
    @Override
    public void delete(int id) {
            String requete="delete from iteneraire where id_it = "+id;
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ItineraireService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @Override
    public void update( List<Object> list,int id) {
        String requete="update iteneraire set pts_depart=?,pts_arrive=?,id_trajet=? where id_it=" + id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, (String) list.get(0));
            pst.setString(2, (String) list.get(1));
            pst.setInt(3,(int)list.get(2));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItineraireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Iteneraire> readAll(){
        List<Iteneraire> list=new ArrayList<>();
        String requete="select * from iteneraire";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            TrajetService ts = new TrajetService();
            while(rs.next()){
                list.add(new Iteneraire(rs.getInt(1),rs.getString(2),rs.getString(3),ts.readByID(rs.getInt(4))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItineraireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    
    public Iteneraire readByID(int id) {
        String requete = "select * from iteneraire where id_it = ?";
        Iteneraire iteneraire =  null;
        try{
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            TrajetService ts = new TrajetService();
            if(rs.next()){
                
                iteneraire = new Iteneraire(rs.getInt(1),rs.getString(2),rs.getString(3),ts.readByID(rs.getInt(4)));

            }

        }catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return iteneraire;
    }
}

