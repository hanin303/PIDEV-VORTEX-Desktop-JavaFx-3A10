/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexionbd.utils.DataSource;
import entity.Ticket;
import java.sql.Connection;
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
public class TicketService implements IService<Ticket>{
    private Connection conn ;
    public TicketService(){
        conn=DataSource.getInstance().getCnx();
    }
    
    public void GenerationTicket(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Ticket t) {
        
//         String requete= "insert into ticket (status,prix,id_reservation) values"+ 
//                "('"+t.getStatus()+"','"+t.getPrix()+"','"+t.getReservation().getId_reservation()+"')";
 String requete= "insert into ticket (id_reservation_id,status,prix) values"+ 
                "('"+t.getReservation().getId_reservation()+"','"+t.getStatus()+"','"+t.getPrix()+"')";
        try {
            Statement ste=conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println(" Ticket ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void delete(int id) {
        try {
            String requete ="delete from ticket where id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
            System.out.println(" Ticket supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Ticket> readAll() {
          String requete ="select * from ticket";
          List<Ticket> list = new ArrayList<>();
         try {
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            ReservationService rss = new ReservationService();
            while(rs.next()){
//          Ticket t = new Ticket(rs.getInt(1),rs.getString(2),rs.getString(3),(rs.getInt(4)));
            Ticket t = new Ticket(rs.getInt(1),rs.getString(3),rs.getString(4),(rs.getInt(2)));

            list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    }

    @Override
    public Ticket readByID(int id) {
//    String requete = "select * from ticket where id_t = ?";
      String requete = "select * from ticket where id = ?";

    Ticket ticket = null;
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        ReservationService rss = new ReservationService();
        if (rs.next()) {
           ticket = new Ticket(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getInt(2));
        }
    } catch (SQLException ex) {
        Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ticket;
}
   
    @Override
    public void update(List<Object> list,int id) {
//         String requete="update ticket set status=?,prix=?, id_reservation=? where id_t="+id;
         String requete="update ticket set id_reservation_id=?,status=?,prix=?  where id="+id;

        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setInt(1, (int) list.get(0));
            pst.setString(2, (String)list.get(1));
            pst.setString(3, (String) list.get(2));
           
            pst.executeUpdate();
            System.out.println(" Ticket modifié !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}

