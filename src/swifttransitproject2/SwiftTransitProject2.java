/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swifttransitproject2;

import connexionbd.utils.DataSource;
import entity.Ligne;
import entity.MoyTran;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import service.LigneService;
import service.MoyTranService;

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
    
           
        /******Creation des objets moyen de transport******/
        
        MoyTran t=new MoyTran(12,15,"train","mercedes","en_service");
        MoyTran t1=new MoyTran(13,30,"bus","bmw","maintenance");
        MoyTran t2=new MoyTran(20,40,"bus","bmw","maintenance");
        MoyTran t3=new MoyTran(20,40,"bus","bmw","maintenance");
        Ligne l=new Ligne("mahdia","ligne_scolaire");
        Ligne l4=new Ligne("Nabeul","ligne_travail");
        LigneService ls =new LigneService();
        //ls.insert(l3);
        //ls.insert(l4);
        MoyTran t4=new MoyTran(1555,40,"bus","bmw","maintenance",17);
        MoyTranService ts=new MoyTranService();
       
      //ts.insert(t4);
       //ts.readAll().forEach(System.out::println); 
       System.out.println("////////////////////////");
       // ts.readByID(11);
       
        System.out.println(ts.readByID(11));
 List<Object> Ilist = new ArrayList<>(Arrays.asList(200000,300000,"métro","wejwej","maintenance",8));
       ts.update(Ilist, 11);
       System.out.println("////////////////////////");
       System.out.println(ts.readByID(11));
        //ts.delete(18);
        //ts.readAll().forEach(System.out::println); 
        //ts.update(6, 1003, 20, "metro", "bmw", "en_service");
       
        /******Creation des ligne******/
        
       // Ligne l=new Ligne("mahdia","ligne_scolaire");
       
       // LigneService ls =new LigneService();
       
       
       // ls.insert(l);
       
        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
       // ls.readAll().forEach(System.out::println); 
       
        //ls.delete(20);
        System.out.println(ls.readByID(1));
        
        
    }
    
}
