/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import entities.Reponse;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import services.ReponseService;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Ajouter_reponseController implements Initializable {

    @FXML
    private TextArea messagetf;
    
ReponseService Rep =  new ReponseService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void repondre(ActionEvent event) {
         try {
           Reponse Rp = new Reponse();

           Rp.setMessage_rep(messagetf.getText());
            Rep.ajouter(Rp);
            System.out.println("Reponse  Ajouté Avec Succès");
        }
       
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }

    }

    @FXML
    private void afficher(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_reponse.fxml"));
            Parent root = (Parent)loader.load();
            Afficher_reponseController controller = (Afficher_reponseController)loader.getController();
           
            messagetf.getScene().setRoot(root);
            
           
             }
         
         catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
        
        
        
    }
    
}
