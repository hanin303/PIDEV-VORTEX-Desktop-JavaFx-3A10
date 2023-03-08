/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import java.sql.*;
import connexionbd.utils.DataSource;

import entity.Ligne;
import entity.MoyTran;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LigneService implements IService<Ligne> {
    
     private Connection conn;

    public LigneService() {
        conn = DataSource.getInstance().getCnx();
    }
     
    

    @Override
    public void insert(Ligne t) {
        
        String requete="insert into ligne (nom_ligne,type_ligne) values"
                + "('"+t.getNom_ligne()+"','"+t.getType_ligne()+"')";
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(LigneService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
         String requete="delete from ligne  where id_ligne= "+id;
            
        try {
             
            Statement st=conn.createStatement();
           
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(LigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Ligne> readAll() {
       
        String requete ="select * from ligne";
        List<Ligne> list=new ArrayList<>();
        try {
            Statement st=conn.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               Ligne t=new Ligne
           (rs.getInt("id_ligne"),rs.getString("nom_ligne"),rs.getString("type_ligne"));
           
               list.add(t);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

   
    @Override
    public void update(List<Object> list, int id) {
       
        String requete="update ligne set nom_ligne=?,type_ligne=? where id_ligne=" + id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, (String) list.get(0));
            pst.setString(2, (String) list.get(1));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Ligne readByID(int id) {
        
       String requete ="select * from ligne where id_ligne = "+ id;
        Ligne m = null;
        try {
            
            Statement st=conn.createStatement();
        
           ResultSet rs= st.executeQuery(requete);
           if(rs.next()){
        
           
           m=new Ligne( rs.getInt("id_ligne"),rs.getString("nom_ligne"),rs.getString("type_ligne"));
           
                             
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

   

   
    
    
}
