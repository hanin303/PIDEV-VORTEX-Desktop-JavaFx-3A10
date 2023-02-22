/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.RoleService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EditUserController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField cin;
    @FXML
    private TextField id;
    @FXML
    private Button modifier;
    UserService us= new UserService();
    
    private int id_user;
    
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void modifierUser(ActionEvent event) throws NullPointerException{
    int id_user=Integer.parseInt(id.getText()) ;
    List<Object> list= new ArrayList<>(Arrays.asList(nom.getText(),prenom.getText(),username.getText(),email.getText(),mdp.getText(),Integer.parseInt(num_tel.getText()),Integer.parseInt(cin.getText())));
    
//     u.setNom(nom.getText());
//     u.setPrenom(prenom.getText());
//     u.setUsername(username.getText());
//     u.setEmail(email.getText());
//     u.setMdp(mdp.getText());
//     u.setNum_tel(Integer.parseInt(num_tel.getText()));
//     u.setCin(Integer.parseInt(cin.getText()));
     
     

     
     us.update(list,id_user );
        
        
     
    }
    
}
