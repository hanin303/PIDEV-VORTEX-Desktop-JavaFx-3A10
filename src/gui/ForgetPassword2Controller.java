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
import javafx.stage.Stage;
import service.UserService;
import javafx.scene.control.PasswordField;
import java.util.Base64;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ForgetPassword2Controller implements Initializable {

    @FXML
    private PasswordField mdp1;
    @FXML
    private PasswordField mdp2;
    @FXML
    private Button changer;
    UserService us= new UserService();
    String email;
    public User u;
    private Stage stage;
    private Scene scene;
    private Parent root;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void getEmail(String email){
        this.email=email;
    }
   
    @FXML
    public void ChangePassword(ActionEvent event) throws IOException{
        Base64.Encoder encoder = Base64.getEncoder();
        if(mdp1.getText().length()>=8){
        if(mdp1.getText().equals(mdp2.getText())){
            us.updatePassword(email,encoder.encodeToString(mdp1.getText().getBytes()));
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("succés");
		alert.setHeaderText("");
		alert.setContentText("votre mot de passe a été changé avec succés");
                alert.showAndWait();
                root = FXMLLoader.load(getClass().getResource("login.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("mot de passe non valide");
           alert.setHeaderText(null);
           alert.setContentText("vous devez saisir un mot de passe valide");
           alert.showAndWait();
        }
        }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Mots de passe non identiques");
           alert.setHeaderText(null);
           alert.setContentText("vous devez saisir des mots de passe identiques");
           alert.showAndWait();
                
                }
    }
private void hidePassword(){
    mdp1.setVisible(false);
    mdp2.setVisible(false);
}

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeVerif.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
