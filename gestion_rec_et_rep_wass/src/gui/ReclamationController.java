/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import entities.Reclamation;
import java.sql.SQLException;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField messagetf;
    @FXML
    private TextField statuttf;
    @FXML
    private TextField typetf;

    
    
    
    ReclamationService Rs = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_reclamation.fxml"));
            Parent root = (Parent)loader.load();
            Afficher_reclamationController controller = (Afficher_reclamationController)loader.getController();
           
            typetf.getScene().setRoot(root);
            messagetf.getScene().setRoot(root);
            statuttf.getScene().setRoot(root);
           
             }
         
         catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
    
    
    
    
    

    @FXML
    private void ajouter(ActionEvent event) {
        
        
        try {
           Reclamation R = new Reclamation();

           R.setMessage_rec(messagetf.getText());
           R.setStatut(statuttf.getText());
           R.setType(typetf.getText());
            Rs.ajouter(R);
            System.out.println("Reclamation  Ajouté Avec Succès");
        }
       
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }

        
        
    }
    
}
