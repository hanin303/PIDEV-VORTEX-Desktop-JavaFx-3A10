/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reclamation;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ReclamationService;

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
    private User u;
    private Stage stage;
    private Scene scene;
    private Parent root; 

    
    
    
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

    public void getUser(User u){
        this.u=u;
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
            Parent root = loader.load();  
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            objetRecl.getScene().setRoot(root);
            messageRecl.getScene().setRoot(root);
            dateRecl.getScene().setRoot(root);
            statutRecl.getScene().setRoot(root);
            Afficher_reclamationController controller = loader.getController();
            controller.getUser(u);
             }
         
         catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
  
}
