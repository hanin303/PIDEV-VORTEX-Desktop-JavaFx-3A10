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


public class Ajouter_reclamationController implements Initializable {

    @FXML
    private TextField objetRecl;
    @FXML
    private TextArea messageRecl;
    @FXML
    private DatePicker dateRecl;
    @FXML
    private TextField statutRecl;
    
    
    
    ReclamationService Rs = new ReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        try{
            Reclamation R = new Reclamation();
            
            R.setObjet(objetRecl.getText());
            R.setMessgae_rec(messageRecl.getText());
            R.setDate_rec(dateRecl.getValue());
            R.setStatut(statutRecl.getText());
            Rs.ajouter(R);
            System.out.println("Reclamation ajoute avec succes");
        }
        catch (SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
    }
        
    

    @FXML
    private void afficher(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/afficher_reclamation.fxml"));
            Parent root = (Parent)loader.load();
            Afficher_reclamationController controller = (Afficher_reclamationController)loader.getController();
            
            objetRecl.getScene().setRoot(root);
            //messageRecl.getScene().setRoot(root);
            //dateRecl.getScene().setRoot(root);
            //statutRecl.getScene().setRoot(root);
            
        }
        catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
    
}
