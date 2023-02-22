/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
import entities.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author DELL
 */
public class ReponseService implements IService <Reponse> {

    
Connection cnx;

    public ReponseService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reponse t) throws SQLException {
          String req = "INSERT INTO reponse (id_reclamation, message_rep, date_rep) VALUES("
                + "'" + t.getId_reclamation() + "','" + t.getMessage_rep() + "','" + t.getDate_rep() + "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public boolean modifier(Reponse t) throws SQLException {
        boolean ok = false ;
        try {
        String req = "UPDATE reponse SET id_reclamation = ?, message_rep = ?, date_rep = ?  where id_reponse = ?";
        PreparedStatement vs = cnx.prepareStatement(req);
        vs.setInt(1, t.getId_reclamation());
        vs.setString(2, t.getMessage_rep());
        vs.setString(3, t.getDate_rep());
        vs.setInt(4, t.getId_reponse());
        vs.executeUpdate();
        ok = true;
        }
        catch (SQLException ex){
            System.out.println("error in delete" + ex);
        
        
        }
        return ok;
    }
    

    @Override
    public boolean supprimer(Reponse t) throws SQLException {
        boolean ok = false;
        try{
       String req = " DELETE FROM reponse where id_reponse = ?   ";
         
            PreparedStatement vs = cnx.prepareStatement(req);
             vs.setInt(1, t.getId_reponse());
            vs.executeUpdate();
            ok = true;
        }
        catch(SQLException ex) {
            System.out.println("EREUR()"+ex);
        }
         return ok;
    }

    @Override
    public List<Reponse> recuperer() throws SQLException {
          List<Reponse> Reponse = new ArrayList<>();
        String s = "select * from reponse ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reponse R = new Reponse();
            
            R.setId_reponse(rs.getInt("id_reponse"));
            R.setId_reclamation(rs.getInt("id_reclamation"));
            R.setMessage_rep(rs.getString("message_rep"));
            R.setDate_rep(rs.getString("date_rep"));
            
            
            
            Reponse.add(R);
            
        }
        return Reponse;
    
    }
    
}
