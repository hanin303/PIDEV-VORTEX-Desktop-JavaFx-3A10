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
import static javax.swing.JOptionPane.showMessageDialog;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Ajouter_reponseController implements Initializable {

    @FXML
    private TextArea reponse;
    @FXML
    private TextField idRec;
    
    private static int id;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User u;
          
          
          public static int getIdRec (Reclamation Rec) {
          id = Rec.getId_reclamation();
          System.out.println(id);
          return id;
        
          }
          
          ReponseService RS = new ReponseService();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
         
     public void getUser(User u){
        this.u=u;
    }     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void repondre(ActionEvent event) {
        
              
        Reponse R = new Reponse();
        try {
             
            
            
            
            R.setId_rec(Integer.parseInt(idRec.getText()));
            R.setText_rep(reponse.getText());
            
            RS.ajouter(R);
                     showMessageDialog(null, "Reponse Envoyé" );  
                     }
        
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
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
