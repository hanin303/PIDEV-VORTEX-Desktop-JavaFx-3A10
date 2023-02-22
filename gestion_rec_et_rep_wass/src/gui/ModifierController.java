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
import services.ReclamationService;
import entities.Reclamation;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField messagetf;
    @FXML
    private TextField statuttf;
    @FXML
    private TextField typetf;
    @FXML
    private TextField idtf;

    
    
    
    ReclamationService RE = new ReclamationService();
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
           
            messagetf.getScene().setRoot(root);
            statuttf.getScene().setRoot(root);
            typetf.getScene().setRoot(root);
           idtf.getScene().setRoot(root);
             }
         
         catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         try {
            Reclamation rec1  = new Reclamation();
           
            rec1.setId_reclamation(Integer.parseInt( idtf.getText()));
            rec1.setMessage_rec((messagetf.getText()));
            rec1.setType(typetf.getText());
            rec1.setStatut(statuttf.getText());
           
            RE.modifier(rec1);
            System.out.println("Reclamtion Modifié Avec Succès");
        }
       
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }
    }
    
}
