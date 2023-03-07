/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Modifier_reclamationController implements Initializable {

    @FXML
    private DatePicker dateRecl;
    @FXML
    private TextField idRecl;
    @FXML
    private TextField objetRecl;
    @FXML
    private TextArea messageRecl;
    @FXML
    private TextField statutRecl;

    
    
    
    ReclamationService RE = new ReclamationService();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try{
            Reclamation recl = new Reclamation();
            
            recl.setId_reclamation(Integer.parseInt(idRecl.getText()));
            recl.setObjet(objetRecl.getText());
            recl.setMessgae_rec(messageRecl.getText());
            recl.setDate_rec(dateRecl.getValue());
            recl.setStatut(statutRecl.getText());
            
            RE.modifier(recl);
            System.out.println("Reclamation Modifie Avec succes");
        }
        
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_reclamation.fxml"));
            Parent root = (Parent)loader.load();
            Afficher_reclamationController controller = (Afficher_reclamationController)loader.getController();
  
            idRecl.getScene().setRoot(root);
            //objetRecl.getScene().setRoot(root);
            //messageRecl.getScene().setRoot(root);
            //dateRecl.getScene().setRoot(root);
            //statutRecl.getScene().setRoot(root);
             }
         
         catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
