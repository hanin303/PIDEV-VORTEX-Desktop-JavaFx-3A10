/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reclamation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connexionbd.utils.DataSource;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wassim
 */
public class ReclamationService implements IService <Reclamation> {
    Connection cnx;
    
    public ReclamationService() {
           cnx =DataSource.getInstance().getCnx();

    }
    
    public void ajouter(Reclamation t) throws SQLException {
    //List<Reclamation> rec = new ArrayList<Reclamation> ();
    /*String req = "INSERT INTO reclamation (objet,message_rec,statut,date_rec) VALUES('"
            + "','" + t.getObjet() + "','" + t.getMessgae_rec() + "','" + t.getStatut() + "','" + t.getDate_rec() + "','" + "')";*/
    String qry = "INSERT INTO reclamation ( objet,message_rec, statut,date_rec) VALUES ('" + t.getObjet() + "', '" + t.getMessgae_rec() + "', '" + t.getStatut() + "', '" + t.getDate_rec() +"')";
    Statement st = cnx.createStatement();
    st.executeUpdate(qry);
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Reclamation> recuperer() throws SQLException {
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
    }

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public boolean supprimer(Reclamation t) throws SQLException {
        
        boolean ok = false;
        try { 
         String req = " DELETE FROM reclamation where id_reclamation = ?   ";
         
            PreparedStatement vs = cnx.prepareStatement(req);
             vs.setInt(1, t.getId_reclamation());
            vs.executeUpdate();
            ok = true;
    }
        catch(SQLException ex) {
            System.out.println("EREUR()"+ex);
        }
        
        return ok;
    }


public boolean modifier(Reclamation t) throws SQLException {
        boolean ok= false;
        try {
        String req = "UPDATE reclamation SET objet = ?,message_rec = ? , statut = ?   where id_reclamation = ?";
        PreparedStatement vs = cnx.prepareStatement(req);
        vs.setString(1, t.getObjet());
        vs.setString(2, t.getMessgae_rec());
        vs.setString(3, t.getStatut());
        //vs.setDate(4, java.sql.Date.valueOf(t.getDate_rec()));
        vs.setInt(4, t.getId_reclamation());
        vs.executeUpdate();
        ok = true; 
        }
        catch (SQLException ex){
            System.out.println("error in delete" + ex);
        }
return ok;
    }

    public ObservableList<Reclamation> search(String input)  {
        ObservableList<Reclamation>reclamations=FXCollections.observableArrayList();
        try {
            
            Statement st;
            st = cnx.createStatement();
            String s = "select * from reclamation where  id_reclamation like '%"+input+"%'";
            
               System.out.println(""+s);
             ResultSet res=st.executeQuery(s);
             while (res.next()){
                 Reclamation R=new Reclamation(res.getInt(1),res.getString(2), res.getString(3), res.getString(4) , res.getDate(5).toLocalDate());
                 /*R.setId_rec(rs.getInt("id_reclamation"));
            R.setSujet(rs.getString("sujet"));
            R.setText_rec(rs.getString("text_rec"));
            R.setId_employe(rs.getInt("id_employe"));
            R.setId_user(rs.getInt("id_user"));*/
                 
                
                 reclamations.add(R);
                     
             }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return reclamations;

        }
    public int Stats(String par,String field) {
     String s  = "select count(*) from reclamation where "+field+""+ "=" +"'"+par+"'";
   
  
      try {

        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);

            int num = 0;
            while(rs.next()){
                num = (rs.getInt(1));
                return num;
 
            }
        } catch (SQLException ex) {
            
        }
        return 0 ;
    }
  /*public Reclamation recherche(int id) {
        Reclamation R = null;
        String Req = "select * from reclamation where id_reclamation= " +id+ "";
                  
   
        try {
            
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req); //recherche
            while (res.next()) {

               R = new Reclamation(res.getString(2), res.getString(3), res.getString(4) , res.getDate(5).toLocalDate());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return R;
    }
    */
    /*public void modifier2(Reclamation t) {
        try {
            String req = "UPDATE `reclamation` SET `objet` = '" + t.getObjet() + "', `message_rec` = '" + t.getMessgae_rec()  + "', `statut` = '" + t.getStatut() + "' WHERE `reclamation`.`id_reclamation` = " + t.getId_reclamation();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamtion updated !");
        }
catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    @Override
    public void insert(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(List<Object> list, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reclamation> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reclamation readByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

