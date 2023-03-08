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
import mailing.SendEmail;
import service.UserService;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ForgetPassword1Controller implements Initializable{

    @FXML
    private TextField email;
    @FXML
    private Button envoyer;
    private User u;    
    UserService us= new UserService();
     private Stage stage;
    private Scene scene;
    private Parent root;


       
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void Send(ActionEvent event) throws IOException{
        String code;
        String email_user= email.getText();
        
        if (email_user!=null && us.readByEmail(email_user)!=null){
        SendEmail email =new SendEmail();
        code= email.VerificationMail(email_user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("succés");
		alert.setHeaderText("");
		alert.setContentText("e-mail de vérification a été envoyé avec succés");
                alert.showAndWait();
                   
           
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeVerif.fxml"));
                Parent root = loader.load(); 
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                CodeVerifController controller = loader.getController();
                controller.getCode(code,email_user);
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("echec");
           alert.setHeaderText(null);
           alert.setContentText("e-mail non valide");
           alert.showAndWait();
        }
    }

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
  

  
   
   
   
    