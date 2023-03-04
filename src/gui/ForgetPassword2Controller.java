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
import javafx.scene.control.TextField;
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
    String email_add;
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
        u=us.readByEmail(email);
        email_add=email;
        System.out.println("forget2:"+email);
        System.out.println("forget2this:"+email_add);

    }
   
    @FXML
    public void ChangePassword(ActionEvent event) throws IOException{
        System.out.println(u);
        String email_add2= email_add;
        System.out.println("forget4:"+email_add2);
        Base64.Encoder encoder = Base64.getEncoder();
        if(mdp1.getText().equals(mdp2.getText())){
            System.out.println("forget5:"+email_add2);
            us.updatePassword(email_add2,encoder.encodeToString(mdp1.getText().getBytes()));
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
        }
        
    }
    @FXML
private void hidePassword(){
    mdp1.setVisible(false);
    mdp2.setVisible(false);
}
    
}
