/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package swifttransitproject2;

 import java.net.Socket;
 
 import javax.sound.sampled.SourceDataLine;
 
 import connexionbd.utils.DataSource;
 import entity.*;
 import service.*;
 import java.util.*;
import java.util.stream.Collectors;
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
         // System.out.println(ds1);
         // DataSource ds2 = DataSource.getInstance();
         // System.out.println(ds2);
         // DataSource ds3 = DataSource.getInstance();
         // System.out.println(ds3);
         Trajet t1 = new Trajet("1h", "Paris", "Lyonss");
         TrajetService ts = new TrajetService();
         List<Object> Tlist = new ArrayList<>(Arrays.asList("2h","nabel","tunis"));
         Iteneraire i1 = new Iteneraire("fff","fff",t1);
         List<Object> Ilist = new ArrayList<>(Arrays.asList("aaaaaaa","aaaaaaa"));
         ItineraireService is = new ItineraireService();
         ts.insert(t1);
         is.insert(i1);
         //is.update(Ilist, 13);
        
         List<Iteneraire> list2 = new ArrayList<>(); 
         List<Iteneraire> list = new ArrayList<>();
        list = is.readAll();
         
         
         ///ts.delete(13);
         //ts.update(Tlist, 20);
         //ts.readAll().forEach(System.out::println);
 
         //System.out.println(ts.readByID(19));
 
     }
     
 }
 