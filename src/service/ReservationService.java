/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexionbd.utils.DataSource;
import entity.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hanin
 */
public class ReservationService implements IService<Reservation>{
    private Connection conn ;
    public ReservationService(){
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reservation r) {
        String requete= "insert into reservation (date_reservation,heure_depart,heure_arrive,status,id_client,id_moy,id_it,type_ticket) values"+ 
                "('"+r.getDate_reservation()+"','"+r.getHeure_depart()+"','"+r.getHeure_arrive()+"','"+r.getStatus()+"','"+r.getId_user()+"','"+r.getId_moy()+"','"+r.getIteneraire().getId()+"','"+r.getType_ticket()+"')";
        
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println(" Reservation ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }    
       
    }

   @Override
    public void delete(int id) {
        try {
            String requete ="delete from reservation where id_reservation ="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
            System.out.println(" Reservation supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     @Override
    public void update(List<Object> list,int id) {
         String requete="update reservation set date_reservation=?,heure_depart=?,heure_arrive=?,status=?,id_client=?,id_moy=?,id_it=?,type_ticket=? where id_reservation="+id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
              
            pst.setDate(1, java.sql.Date.valueOf((String) list.get(0)));
            //pst.setDate(1, (Date) list.get(0));  
            pst.setString(2, (String) list.get(1));
            pst.setString(3, (String) list.get(2));
            pst.setString(4, (String) list.get(3));
            pst.setInt(5, (int) list.get(4));
            pst.setInt(6, (int) list.get(5));
            pst.setInt(7, (int) list.get(6));
            pst.setString(8, (String) list.get(7));
            pst.executeUpdate();
            System.out.println(" Reservation modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public List<Reservation> readAll() {
         String requete ="select * from reservation";
            List<Reservation> list = new ArrayList<>();
         try {
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            ItineraireService ts = new ItineraireService();
            while(rs.next()){

           Reservation r = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getString(3), rs.getString(4),rs.getInt(6),rs.getInt(7),ts.readByID(rs.getInt(8)),rs.getString(9));
           list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    
}
      
    
    
    public Reservation readByID(int id) {
        String requete = "select * from reservation where id_reservation = ?";
        Reservation reservation =  null;
        try{
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            ReservationService rss = new ReservationService();
            ItineraireService ts = new ItineraireService();
            if(rs.next()){
              reservation = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getString(3), rs.getString(4),rs.getInt(6),rs.getInt(7),ts.readByID(rs.getInt(8)),rs.getString(9));
            }
        }catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }


}

