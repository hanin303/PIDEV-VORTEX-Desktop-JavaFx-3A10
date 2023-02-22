/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Role;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import static jdk.nashorn.tools.ShellFunctions.input;
import service.RoleService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
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
    private RadioButton role1;
     
    @FXML
    private RadioButton role2;
    @FXML
    private RadioButton role3;
    @FXML
    private Button inscri;
    
    private UserService us;
    private RoleService rs;
    private ToggleGroup roleGroup;
    @FXML
    private ToggleGroup role;
        
    int max=8;
    

    private boolean EmailValid(String email){
        if(email==null||email.isEmpty()){
            return false;
        }
        Pattern pattern= Pattern.compile("^[\\w\\d._%+-]+@[\\w\\d.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public boolean NumberValid(String num_tel) {
    // Expression régulière pour un numéro de téléphone de 8 chiffres (sans les indicateurs de pays)
    String regex = "^[0-9]{8}$";
    return num_tel.matches(regex);
}
    
    public boolean CinValid(String cin) {
    // Expression régulière pour un numéro de téléphone de 8 chiffres (sans les indicateurs de pays)
    String regex = "^[0-9]{8}$";
    return cin.matches(regex);
}
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rs= new RoleService();
        us= new UserService();
        roleGroup= new ToggleGroup();
        role1.setToggleGroup(roleGroup);
        role2.setToggleGroup(roleGroup);
        role3.setToggleGroup(roleGroup);

    }
    
    @FXML
    public void ajouterUser(ActionEvent event){
       String newNom=nom.getText();
       String newPrenom=prenom.getText();
       String newUsername=username.getText();
       String newEmail=email.getText();
       String newMdp=mdp.getText();
       String newNumber = num_tel.getText();
       String newCin=cin.getText();
        
         Role role=null;
        if(role1.isSelected()){
          role=rs.readByID(2);
        }
        else if(role2.isSelected()){
          role=rs.readByID(2);
        }
        else if(role3.isSelected()){
          role=rs.readByID(3);
        }
       
       if(newNom.isEmpty()||newPrenom.isEmpty()||newUsername.isEmpty()||newEmail.isEmpty()||newMdp.isEmpty()||newNumber.isEmpty()||newCin.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
       }else{
       if(EmailValid(newEmail)){
         if(NumberValid(newNumber)){
             if(CinValid(newCin)){
        User u = new User(nom.getText(),prenom.getText(),username.getText(),email.getText(),mdp.getText(),Integer.parseInt(num_tel.getText()),Integer.parseInt(cin.getText()),role);
        us.insert(u);
        clearFields();
             }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("numéro de carte identité non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez un numéro de carte identité valide");
           alert.showAndWait();
             }
         }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("numéro de téléphone non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez un numéro de téléphone valide");
           alert.showAndWait();
             
         }
           }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("e-mail non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une adresse e-mail valide");
           alert.showAndWait();
       }
       }
       
      
    }    
private void clearFields(){
    nom.setText("");
    prenom.setText("");
    username.setText("");
    email.setText("");
    mdp.setText("");
    num_tel.setText("");
    cin.setText("");
    roleGroup.selectToggle(null);
    
}   

}

   
