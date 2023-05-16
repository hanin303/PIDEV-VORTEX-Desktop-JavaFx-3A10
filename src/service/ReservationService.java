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
import java.time.LocalDate;
import java.time.LocalTime;
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
//        String requete= "insert into reservation (date_reservation,heure_depart,heure_arrive,status,id_client,id_moy,id_it,type_ticket) values"+ 
//                "('"+r.getDate_reservation()+"','"+r.getHeure_depart()+"','"+r.getHeure_arrive()+"','"+r.getStatus()+"','"+r.getId_user()+"','"+r.getId_moy()+"','"+r.getIteneraire().getId()+"','"+r.getType_ticket()+"')";
          String requete= "insert into reservation (id_client_id,id_moy_id,id_it_id,date_reservation,heure_depart,heure_arrive,status,type_ticket) values"+ 
                "('"+r.getId_user()+"','"+r.getId_moy()+"','"+r.getIteneraire().getId()+"','"+r.getDate_reservation()+"','"+r.getHeure_depart()+"','"+r.getHeure_arrive()+"','"+r.getStatus()+"','"+r.getType_ticket()+"')";
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
//            String requete ="delete from reservation where id_reservation ="+id;
            String requete ="delete from reservation where id ="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
            System.out.println(" Reservation supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


     @Override
    public void update(List<Object> list,int id) {
//         String requete="update reservation set date_reservation=?,heure_depart=?,heure_arrive=?,status=?,id_client=?,id_moy=?,id_it=?,type_ticket=? where id_reservation="+id;
         String requete="update reservation set id_client_id=?,id_moy_id=?,id_it_id=?,date_reservation=?,heure_depart=?,heure_arrive=?,status=?,type_ticket=? where id="+id;

//        try {
//            PreparedStatement pst=conn.prepareStatement(requete);
//              
//            pst.setDate(1, java.sql.Date.valueOf((LocalDate)list.get(0)));
//            //pst.setString(2, (String) list.get(1));
//            //pst.setString(3, (String) list.get(2));
//            pst.setTime(2, java.sql.Time.valueOf((LocalTime)list.get(1)));
//            pst.setTime(3, java.sql.Time.valueOf((LocalTime)list.get(2)));
//            pst.setString(4, (String) list.get(3));
//            pst.setInt(5, (int) list.get(4));
//            pst.setInt(6, (int) list.get(5));
//            pst.setInt(7, (int) list.get(6));
//            pst.setString(8, (String) list.get(7));
//            pst.executeUpdate();
//            System.out.println(" Reservation modifié !");
//        } catch (SQLException ex) {
//            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
 try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setInt(1, (int) list.get(0));
            pst.setInt(2, (int) list.get(1));
            pst.setInt(3, (int) list.get(2));  
            pst.setDate(4, java.sql.Date.valueOf((LocalDate)list.get(3)));
            //pst.setString(2, (String) list.get(1));
            //pst.setString(3, (String) list.get(2));
            pst.setTime(5, java.sql.Time.valueOf((LocalTime)list.get(4)));
            pst.setTime(6, java.sql.Time.valueOf((LocalTime)list.get(5)));
            pst.setString(7, (String) list.get(6));
            pst.setString(8, (String) list.get(7));
            pst.executeUpdate();
            System.out.println(" Reservation modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    //badaltlena
    @Override
    public List<Reservation> readAll() {
         String requete ="select * from reservation";
            List<Reservation> list = new ArrayList<>();
         try {
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            ItineraireService ts = new ItineraireService();
            while(rs.next()){
           UserService us= new UserService();
//         Reservation r = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getTime(3), rs.getTime(4),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
           Reservation r = new Reservation(rs.getInt(1), rs.getString(8), rs.getDate(5).toLocalDate(), rs.getTime(6), rs.getTime(7),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(9));

           list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    
}
    //badaltlena
    public Reservation readByID(int id) {
        String requete = "select * from reservation where id = ?";
        Reservation reservation =  null;
        try{
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            ReservationService rss = new ReservationService();
            ItineraireService ts = new ItineraireService();
            if(rs.next()){
            //reservation = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getString(3), rs.getString(4),rs.getInt(6),rs.getInt(7),ts.readByID(rs.getInt(8)),rs.getString(9));
//            reservation = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getTime(3), rs.getTime(4),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
            reservation = new Reservation(rs.getInt(1), rs.getString(8), rs.getDate(5).toLocalDate(), rs.getTime(6), rs.getTime(7),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(9));

            }
        }catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }
    public Reservation readByID_user(int id) {
        List<Reservation> list = new ArrayList<>();
        String requete = "select * from reservation where id_client = ?";
        Reservation reservation =  null;
        try{
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(7,id);
            ResultSet rs = pst.executeQuery();
            ReservationService rss = new ReservationService();
            ItineraireService ts = new ItineraireService();
            UserService us = new UserService();
            if(rs.next()){
//            reservation = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getTime(3), rs.getTime(4),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
            reservation = new Reservation(rs.getInt(1), rs.getString(8), rs.getDate(5).toLocalDate(), rs.getTime(6), rs.getTime(7),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(9));

            list.add(reservation);
            //reservation = new Reservation(rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getString(3), rs.getString(4),rs.getInt(6),rs.getInt(7),ts.readByID(rs.getInt(8)),rs.getString(9));
//            reservation = new Reservation(rs.getInt(1),rs.getInt(1), rs.getString(5), rs.getDate(2).toLocalDate(), rs.getString(3), rs.getString(4),rs.getInt(6),rs.getInt(8),rs.getString(9));
            }
        }catch (SQLException ex) {
            Logger.getLogger(TrajetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }
    public void update2(Reservation r ,int id) {
//         String requete="update reservation set date_reservation=?,heure_depart=?,heure_arrive=?,status=?,id_client=?,id_moy=?,id_it=?,type_ticket=? where id_reservation="+id;
         String requete="update reservation set id_client_id=?,id_moy_id=?,id_it_id=?,date_reservation=?,heure_depart=?,heure_arrive=?,status=?,type_ticket=? where id="+id;

//        try {
//            PreparedStatement pst=conn.prepareStatement(requete);
//             //badalt lena  
//            pst.setDate(1, (java.sql.Date.valueOf(r.getDate_reservation())));
//            pst.setTime(2,r.getHeure_depart());
//            pst.setTime(3, r.getHeure_arrive());
//            pst.setString(4, r.getStatus());
//            pst.setInt(5, r.getId_user());
//            pst.setInt(6, r.getId_moy());
//            pst.setInt(7, r.getId_it());
//            pst.setString(8, r.getType_ticket());
//            pst.executeUpdate();
//            System.out.println(" Reservation modifié !");
//        } catch (SQLException ex) {
//            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//    }
 try {
            PreparedStatement pst=conn.prepareStatement(requete);
             //badalt lena  
            pst.setInt(1, r.getId_user());
            pst.setInt(2, r.getId_moy());
            pst.setInt(3, r.getId_it());
            pst.setDate(4, (java.sql.Date.valueOf(r.getDate_reservation())));
            pst.setTime(5,r.getHeure_depart());
            pst.setTime(6, r.getHeure_arrive());
            pst.setString(7, r.getStatus());
            pst.setString(8, r.getType_ticket());
            pst.executeUpdate();
            System.out.println(" Reservation modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
