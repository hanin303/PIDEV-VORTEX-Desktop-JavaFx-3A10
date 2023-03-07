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
 * @author wassim
 */
public class ReponseService {
    
    Connection cnx;
    
    public ReponseService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    public void ajouter(Reponse t) throws SQLException {
          String req = "INSERT INTO reponse (id_reclamation,text_rep) VALUES("
                + "'" + t.getId_rec() + "','" + t.getText_rep()+  "'"  +  ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    
    
    public void modifier(Reponse t) throws SQLException {
        String req = "UPDATE reponse SET  text_rep = ? where id_reponse = ?";
        PreparedStatement vs = cnx.prepareStatement(req);
       
        //vs.setInt(1, t.getId_rec());
        vs.setString(1, t.getText_rep());
        vs.setInt(2, t.getId_rep());
        vs.executeUpdate();
        
    }
    /*public void modifier(Reponse t) throws SQLException {
        String req = "UPDATE reponse SET id_reclamation = ? , text_rep = ? where id_reponse = ?";
        PreparedStatement vs = cnx.prepareStatement(req);
       
        vs.setInt(1, t.getId_rec());
        vs.setString(2, t.getText_rep());
        vs.setInt(3, t.getId_rep());
        vs.executeUpdate();
        
    }*/
    
    public boolean supprimer(Reponse t) throws SQLException {
        boolean ok = false;
        try {
        String req = " DELETE FROM reponse where id_reponse = ?   ";
         
            PreparedStatement vs = cnx.prepareStatement(req);
             vs.setInt(1, t.getId_rep());
            vs.executeUpdate();
            ok= true;
        }
        catch ( SQLException ex){
            System.out.println("error in delete"+ex);
        
           
        }
        return ok;
    }
    
    public List<Reponse> recuperer() throws SQLException {
          List<Reponse> Reponse = new ArrayList<>();
        String s = "select * from reponse ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reponse R = new Reponse();
            
            R.setId_rep(rs.getInt("id_Reponse"));
            R.setId_rec(rs.getInt("id_reclamation"));
            R.setText_rep(rs.getString("text_rep"));
      
            
            
            
            Reponse.add(R);
            
        }
        return Reponse;
    
}

    /*public List<Reclamation> recuperer2() throws SQLException {
        List<Reclamation> Reclamation = new ArrayList<>();
        String s = "select * from reclamation ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while(rs.next()){
        Reclamation R = new Reclamation();
        
        R.setId_reclamation(rs.getInt("id_reclamation"));
        R.setObjet(rs.getString("objet"));
        R.setMessgae_rec(rs.getString("message_rec"));
        R.setDate_rec(rs.getDate("date_rec").toLocalDate());
        R.setStatut(rs.getString("statut"));
        
        Reclamation.add(R);
       
        }
        return Reclamation;
    }*/
    
}
