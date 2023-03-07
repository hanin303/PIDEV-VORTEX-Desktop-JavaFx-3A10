/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSendAction(ActionEvent event) {
        EmailSender.sendEmail(from.getText(), password.getText(), "wassim.hassayoune@esprit.tn", subject.getText(), body.getText());
        
    }
    
}
