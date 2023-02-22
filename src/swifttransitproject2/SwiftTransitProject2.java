/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swifttransitproject2;

import connexionbd.utils.DataSource;
import entity.Reservation;
import entity.Ticket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import service.ReservationService;
import service.TicketService;

/**
 *
 * @author hanin
 */
public class SwiftTransitProject2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
        DataSource ds2 = DataSource.getInstance();
        System.out.println(ds2);
        DataSource ds3 = DataSource.getInstance();
        System.out.println(ds3);
        
//Reservation r1 = new Reservation(Reservation.Status.confirme, Date.valueOf("2022-02-13"), 12.30 , 13.15,1,1,10,"aller_simple");
//Reservation r2 = new Reservation(Reservation.Status.confirme, "2022-02-13" , 12.30 , 13.15,1,1,10,"aller_simple");
ReservationService rs = new ReservationService();
//rs.insert(r1);
//List<Object> Ilist = new ArrayList<>(Arrays.asList( Date.valueOf("2023-02-13"), 14.30, 12.30 , Reservation.Status.en_attente,"aller_simple"));
//rs.update(Ilist,1); 
//rs.delete(16);

//rs.readAll().forEach(System.out::println);
System.out.println(rs.readByID(3));

//Ticket t1 = new Ticket(Ticket.Status.non_paye , 17 , 1);
//Ticket t2 = new Ticket(Ticket.Status.non_paye , 20 ,1);
//Ticket t3 = new Ticket(Ticket.Status.non_paye , 7 , 1);
//TicketService ts = new TicketService();
//ts.insert(t3);
//ts.delete(3);

//List<Object> Ilist = new ArrayList<>(Arrays.asList(Ticket.Status.paye,20));
//ts.update(Ilist,2); 

//ts.readAll().forEach(System.out::println);
//System.out.println(ts.readByID(2));
        
        
        
        
        
        
    }
    
}
