/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.Reclamation;
import entities.Reponse;
import java.sql.SQLException;
import services.ReclamationService;
import services.ReponseService;
import utils.MyDB;
/**
 *
 * @author DELL
 */
public class Test {
    
    
     public static void main(String[] args) {
       
       // try {
            
            
            ///////////////////////////////////// RECLMATION //////////////////////////////////////
            
            
             Reclamation R1 = new Reclamation(1, "bonjour", "type", "en cours");
            ReclamationService Rs = new ReclamationService() ; 
            //Rs.ajouter(R1);
            //Rs.modifier(R);
            //Rs.supprimer(R);
//              System.out.println(Rs.recuperer(R1));
              
              
              
              
              //////////////////////////////// REPONSE ////////////////////////
              
              
        
            Reponse R = new Reponse(2 , 1 , " arwa ali doit repondre a votre reclamation " , "12-10-2023");
            ReponseService RepS = new ReponseService();
           // RepS.ajouter(R);
            //RepS.modifier(R);
            //RepS.supprimer(R);
            
            
            
           // System.out.println(RepS.recuperer(R));
        //}
        //catch (SQLException ex) {
          //  System.out.println(ex.getMessage());
        //}
    

    
}
     
}


