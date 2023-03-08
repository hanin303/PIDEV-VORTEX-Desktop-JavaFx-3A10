/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reclamation;
import entity.Reponse;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ReponseService;

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
    private User u;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    
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
    public void getUser(User u){
        this.u=u;
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
            Afficher_reponseController controller2 = loader.getController();
            controller2.getUser(u);
  
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

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageAdmin.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageAdminController controller = loader.getController();
        controller.setFields(u);
    }
    
    
}
