/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Modifier_reponseController implements Initializable {

    @FXML
    private TextField idRep;
    @FXML
    private TextArea messageRep;
    
    
    
    ReponseService RE = new ReponseService();

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
            Reponse repl = new Reponse();
            
            repl.setId_rep(Integer.parseInt(idRep.getText()));
            repl.setText_rep(messageRep.getText());
            
            RE.modifier(repl);
            System.out.println("Reponse Modifie Avec succes");
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
  
            idRep.getScene().setRoot(root);
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
