/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LoginController implements Initializable {
    @FXML
    private TextField username_column;
    @FXML
    private PasswordField mdp_column;
    private Stage stage;
    private Scene scene;
    private Parent root;
    UserService us= new UserService();
    @FXML
    private Button auth;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
    
    private boolean authentification(String username, String mdp) {
    User u= us.readByUsername(username_column.getText());
    if((u != null) && (u.getMdp().equals(mdp_column.getText()))) {
        return true;
    } else {
        return false;
    }
}
//    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("succés");
//		alert.setHeaderText("");
//		alert.setContentText("connecté avec succéss");
//                alert.showAndWait();
   @FXML 
   public void connect(ActionEvent event)throws IOException{
      if (authentification(username_column.getText(), mdp_column.getText())) {
            
      User u= us.readByUsername(username_column.getText());
          System.out.println(u);
         if(u.getRole().getId_role()==2){
          root = FXMLLoader.load(getClass().getResource("HomePageAdminStation.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
                }else if(u.getRole().getId_role()==3){
                    root = FXMLLoader.load(getClass().getResource("HomePageConducteur.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();       
                }else if(u.getRole().getId_role()==4){
                     root = FXMLLoader.load(getClass().getResource("HomePageClient.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();    
                }else{
                   Alert alert_erreur1= new Alert(Alert.AlertType.ERROR);
                   alert_erreur1.setTitle("Accés non autorisé");
                   alert_erreur1.setHeaderText(null);
                   alert_erreur1.setContentText("pas d'utilisateur");
                   alert_erreur1.showAndWait();
                }
} else {
           Alert alert_erreur2= new Alert(Alert.AlertType.ERROR);
           alert_erreur2.setTitle("Accés non autorisé");
           alert_erreur2.setHeaderText(null);
           alert_erreur2.setContentText("vous devez vérifier vos informations");
           alert_erreur2.showAndWait();
}

   }

    @FXML
    private void switchForgetPassword1(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("ForgetPassword1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void hidePassword(){
         mdp_column.setVisible(false);
}
    
    
}
