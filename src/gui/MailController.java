/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.EmailSender;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class MailController implements Initializable {

    @FXML
    private TextField from;
    @FXML
    private TextField password;
    @FXML
    private TextField subject;
    @FXML
    private TextField body;
    @FXML
    private Button bntSend;
    private User u;
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
    
    public void getUser(User u){
        this.u=u;
    }

    @FXML
    private void btnSendAction(ActionEvent event) {
        EmailSender.sendEmail(from.getText(), password.getText(), "wassim.hassayoune@esprit.tn", subject.getText(), body.getText());
        
    }

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        if(u.getRole().getId_role()==3){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageConducteur.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageConducteurController controller = loader.getController();
        controller.setFields(u);
    }else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageClient.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageClientController controller = loader.getController();
        controller.setFields(u);
        }
    }
    
}
