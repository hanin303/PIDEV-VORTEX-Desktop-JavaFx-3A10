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



public class MoyTranService implements IService<MoyTran> {
    
    private Connection conn;

    public MoyTranService() {
        conn = DataSource.getInstance().getCnx();
    }
    

    @Override
    public void insert(MoyTran t) {
        
       String requete="insert into moyentransport (matricule,capacite,type_vehicule,marque,etat,id_ligne) values"
                + "('"+t.getMatricule()+"','"+t.getCapacite()+"','"+t.getType_vehicule()+"','"+ t.getMarque()+"','"+t.getEtat()+"','"+t.getId_ligne()+"')";
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void delete(int id) {
       
        String requete="delete from moyentransport  where id_moy= "+id;
            
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

    @Override
    public List<MoyTran> readAll() {
         String requete ="SELECT moyentransport.*,ligne.id_ligne,ligne.nom_ligne,ligne.type_ligne FROM moyentransport INNER JOIN ligne on moyentransport.id_ligne=ligne.id_ligne";
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
          
            
           
            
            MoyTran m = new MoyTran(rs.getInt("id_moy"),rs.getInt("matricule"),rs.getInt("capacite"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat"),rs.getInt("id_ligne"),l.readByID(rs.getInt("id_ligne")));
                
               list.add(m);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

    

    @Override
    public void update(List<Object> list, int id) {
        
        String requete="update moyentransport set matricule = ?, capacite = ? , type_vehicule = ?,marque = ?,etat = ?, id_ligne= ? where id_moy=" + id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setInt(1, (int) list.get(0));
            pst.setInt(2, (int) list.get(1));
            pst.setString(3, (String) list.get(2));
            pst.setString(4, (String) list.get(3));
            pst.setString(5, (String) list.get(4));
            pst.setInt(6, (int) list.get(5));
            
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
      
       String requete ="select * from moyentransport where id_moy = "+ id;
        MoyTran m = null;
        try {
            
            Statement st=conn.createStatement();
         //  PreparedStatement pst = conn.prepareStatement(requete);
          //  pst.setInt(1,id);
           ResultSet rs= st.executeQuery(requete);
           if(rs.next()){
             
           //MoyTran m = new MoyTran();
               LigneService l = new LigneService();
//                     
//            l.setId_ligne(rs.getInt("id_ligne"));
//            l.setNom_ligne(rs.getNString("nom_ligne"));
//            l.setType_ligne(rs.getString("type_ligne"));
           
           m=new MoyTran( rs.getInt("id_moy"),rs.getInt("matricule"),rs.getInt("capacite"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat"),rs.getInt("id_ligne"),l.readByID(rs.getInt("id_ligne")));
           
                             
           }
        } catch (SQLException ex) {
            Logger.getLogger(MoyTranService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
        
    }
    
   
    
}
