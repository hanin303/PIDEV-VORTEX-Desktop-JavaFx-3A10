/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexionbd.utils.DataSource;
import entity.MoyTran;
import entity.Ligne;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class MoyTranService implements IService<MoyTran> {
    
    private Connection conn;

    public MoyTranService() {
        conn = DataSource.getInstance().getCnx();
    }
    

    @Override
    public void insert(MoyTran t) {
        
       String requete="insert into moyen_transport (id_ligne_id ,station_id,matricule,num,capacite,type_vehicule,marque,etat) values"
                + "('"+t.getId_ligne()+"','" +t.getId_station()+"','"+t.getMatricule()+"','"+t.getNum()+"','"+t.getCapacite()+"','"+t.getType_vehicule()+"','"+ t.getMarque()+"','"+t.getEtat()+"')";
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
        }catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void delete(int id) {
       
        String requete="delete from moyen_transport  where id= "+id;
            
        try {
             
            Statement st=conn.createStatement();
           
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void update( int id,int  matricule,int capacite, String type_vehicule, String marque, String etat) {
//       String requete = " UPDATE moyentransport SET " + "  matricule= ?, capacite = ? , type_vehicule = ?,marque = ?,etat = ? WHERE id_moy= "+ id;
//        
//         try {
//            
//           PreparedStatement pst=conn.prepareStatement(requete);
//            pst.setInt(1, matricule);
//            pst.setInt(2, capacite);
//            pst.setString(3,type_vehicule);
//            pst.setString(4, marque);
//            pst.setString(5,etat);
//            pst.executeUpdate();
//            System.out.println("Moyen de transport  modifié !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//             
//    }
             //String requete ="SELECT moyen_transport.*,ligne.id,ligne.nom_ligne,ligne.type_ligne FROM moyen_transport INNER JOIN ligne on moyen_transport.id=ligne.id";

//SELECT moyen_transport.*,ligne.id,ligne.nom_ligne,ligne.type_ligne FROM moyen_transport INNER JOIN ligne on moyen_transport.id=ligne.id 
    @Override
    public List<MoyTran> readAll() {
         String requete ="SELECT * from moyen_transport ";
        List<MoyTran> list=new ArrayList<>();
        try {
            Statement st=conn.createStatement();
           ResultSet rs= st.executeQuery(requete);
           //LigneService ls;
            // ls = new LigneService();
           while(rs.next()){
             // MoyTran t=new MoyTran();
              // Ligne l=new Ligne  
           LigneService l = new LigneService();
//         // t.setId_moy(rs.getInt("id_moy"));
//           t.setMatricule(rs.getInt("matricule"));
//          t.setCapacite(rs.getInt("capacite"));
//           t.setType_vehicule(rs.getString("type_vehicule"));
//           t.setMarque(rs.getString("marque"));
//           t.setEtat(rs.getString("etat"));
//        //   t.getClass(Ligne.class.g)rs.getString("nom_ligne");
//           rs.getString("type_ligne");
          
           
            
            MoyTran m = new MoyTran(rs.getInt("id"),rs.getInt("matricule"),rs.getInt("num"),rs.getInt("capacite"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat"),rs.getInt("id_ligne_id"),rs.getInt("station_id"));
                
               list.add(m);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

    

    @Override
    public void update(List<Object> list, int id) {
        
        String requete="update moyen_transport set  id_ligne_id=?,station_id=?,matricule =?, num =? , capacite =? , type_vehicule =?,marque =?,etat =? where id="+id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setInt(1, (int) list.get(0));
            pst.setInt(2, (int) list.get(1));
            pst.setInt(3, (int) list.get(2));
            pst.setInt(4, (int) list.get(3));
            pst.setInt(5, (int) list.get(4));
            pst.setString(6, (String) list.get(5));
            pst.setString(7, (String) list.get(6));
            pst.setString(8, (String) list.get(7));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    
    @Override
    public MoyTran readByID(int id) {
         //String requete ="select * from moyentransport where id_moy="+id;
        //List<MoyTran> list=new ArrayList<>();
        //String requete ="select* from moyentransport where id_moy= '"+id+"' " ;
      
       String requete ="select * from moyen_transport where id= "+ id;
        MoyTran m = null;
        try {
            
            Statement st=conn.createStatement();
         //  PreparedStatement pst = conn.prepareStatement(requete);
          //  pst.setInt(1,id);
           ResultSet rs= st.executeQuery(requete);
           if(rs.next()){
             
           //MoyTran m = new MoyTran();
               LigneService l = new LigneService();
//                     ,
//            l.setId_ligne(rs.getInt("id_ligne"));
//            l.setNom_ligne(rs.getNString("nom_ligne"));
//            l.setType_ligne(rs.getString("type_ligne"));
           
           m=new MoyTran( rs.getInt("id"),rs.getInt("matricule"),rs.getInt("num"),rs.getInt("capacite"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat"),rs.getInt("id_ligne_id"));
           
                             
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
        
    }
    
    
    public MoyTran readMatricule(int matricule) {
         //String requete ="select * from moyentransport where id_moy="+id;
        //List<MoyTran> list=new ArrayList<>();
        //String requete ="select* from moyentransport where id_moy= '"+id+"' " ;
      
       String requete ="select * from moyen_transport where matricule = "+ matricule;
        MoyTran m = null;
        try {
            
            Statement st=conn.createStatement();
         //  PreparedStatement pst = conn.prepareStatement(requete);
          //  pst.setInt(1,id);
           ResultSet rs= st.executeQuery(requete);
           if(rs.next()){
             
           //MoyTran m = new MoyTran();
               LigneService l = new LigneService();
//                     ,
//            l.setId_ligne(rs.getInt("id_ligne"));
//            l.setNom_ligne(rs.getNString("nom_ligne"));
//            l.setType_ligne(rs.getString("type_ligne"));
           
           m=new MoyTran( rs.getInt("id"),rs.getInt("matricule"),rs.getInt("num"),rs.getInt("capacite"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat"),rs.getInt("id_ligne_id"));
           
                             
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
        
    }
    
    
   
    
}
