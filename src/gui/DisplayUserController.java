/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Role;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.RoleService;
import service.UserService;


/**
 * FXML Controller class
 *
 * @author MSI
 */




public class DisplayUserController implements Initializable {
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User,Integer> id_user;
    @FXML
    private TableColumn<User,String> nom;
    @FXML
    private TableColumn<User,String> prenom;
    @FXML
    private TableColumn<User,String> username;
    @FXML 
    private TableColumn<User,String> email;
    @FXML 
    private TableColumn<User,String> mdp;
    @FXML
    private TableColumn<User,Integer> num_tel;
    @FXML
    private TableColumn<User,Integer> cin;
    @FXML 
    private TableColumn<User,String> image;
    @FXML
    private TableColumn<User,Role> role;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    
    @FXML
    private TextField nom_up;
    @FXML
    private TextField prenom_up;
    @FXML
    private TextField username_up;
    @FXML
    private TextField email_up; 
    @FXML
    private TextField num_tel_up;
    @FXML
    private TextField mdp_up;
    @FXML
    private TextField cin_up;
    @FXML
    private ComboBox combo_role;
    @FXML
    private Button ajouter;
   
    UserService us= new UserService();
    RoleService rs= new RoleService();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField id;
    @FXML
    private Button search;

    
    


    
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
    
    

        public void display(){   
        List<User> list= new ArrayList<>();
        list=us.readAll();   
        ObservableList<User> list_user=FXCollections.observableArrayList(list);
        id_user.setCellValueFactory(new PropertyValueFactory<User,Integer>("id_user"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        mdp.setCellValueFactory(new PropertyValueFactory<User,String>("mdp"));
        num_tel.setCellValueFactory(new PropertyValueFactory<User,Integer>("num_tel"));
        cin.setCellValueFactory(new PropertyValueFactory<User,Integer>("cin"));
        image.setCellValueFactory(new PropertyValueFactory<User,String>("Image"));
        role.setCellValueFactory(new PropertyValueFactory<User,Role>("role"));
        tableUsers.setItems(list_user);
        tableUsers.refresh();

    } 

        
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        display();
        ObservableList<Role> roles = FXCollections.observableArrayList(rs.readAll());
        List<Integer> id_role = roles.stream().map(Role::getId_role).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_role);
        combo_role.setItems(observableIds);
    }
    
    @FXML
    public void supprimerUser(ActionEvent event){
        User u= tableUsers.getSelectionModel().getSelectedItem();
        us.delete(u.getId_user());
        display();
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("suppression");
		alert.setHeaderText("");
		alert.setContentText("utilisateur supprimé avec succés");
                alert.showAndWait();
        
    }
    @FXML
    public void modifierUser(ActionEvent event){
       String newNom=nom_up.getText();
       String newPrenom=prenom_up.getText();
       String newUsername=username_up.getText();
       String newEmail=email_up.getText();
       String newMdp=mdp_up.getText();
       String newNumber = num_tel_up.getText();
       String newCin=cin_up.getText();

       
       if(newNom.isEmpty()||newPrenom.isEmpty()||newUsername.isEmpty()||newEmail.isEmpty()||newMdp.isEmpty()||newNumber.isEmpty()||newCin.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
       }else{    
       if(EmailValid(newEmail)){
         if(NumberValid(newNumber)){
             if(CinValid(newCin)){
                 if(newMdp.length()>=8){
        List<Object> list= new ArrayList<>(Arrays.asList(nom_up.getText(),prenom_up.getText(),username_up.getText(),email_up.getText(),mdp_up.getText(),Integer.parseInt(num_tel_up.getText()),Integer.parseInt(cin_up.getText()),null));
        User u= tableUsers.getSelectionModel().getSelectedItem();
        us.updateWithoutImage(list, u.getId_user());
        display();
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("mise à jour");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
                clearFields();
                 }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("mot de passe invalide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une mot de passe valide");
           alert.showAndWait();
                 }

             }else{
                 Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("numéro de carte identité non valide");
                 alert.setHeaderText(null);
                 alert.setContentText("Vous devez entrez un numéro de carte identité valide");
                 alert.showAndWait();
             }
             }else{
                     Alert alert= new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("numéro de téléphone non valide");
                     alert.setHeaderText(null);
                     alert.setContentText("Vous devez entrez un numéro de téléphone valide");
                     alert.showAndWait();
             
                     }
             }else{
               Alert alert= new Alert(Alert.AlertType.ERROR);
               alert.setTitle("e-mail non valide");
               alert.setHeaderText(null);
               alert.setContentText("Vous devez entrez une adresse e-mail valide");
               alert.showAndWait();   
             }
       }

    }
    
    @FXML
    public void ajouterUser(ActionEvent event){
       String newNom=nom_up.getText();
       String newPrenom=prenom_up.getText();
       String newUsername=username_up.getText();
       String newEmail=email_up.getText();
       String newMdp=mdp_up.getText();
       String newNumber = num_tel_up.getText();
       String newCin=cin_up.getText();
       if(newNom.isEmpty()||newPrenom.isEmpty()||newUsername.isEmpty()||newEmail.isEmpty()||newMdp.isEmpty()||newNumber.isEmpty()||newCin.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
       }else{       
       if(EmailValid(newEmail)){
         if(NumberValid(newNumber)){
             if(CinValid(newCin)){
                 if(newMdp.length()>=8){
        Role role=rs.readByID((int) combo_role.getValue());
        User u = new User(nom_up.getText(),prenom_up.getText(),username_up.getText(),email_up.getText(),mdp_up.getText(),Integer.parseInt(num_tel_up.getText()),Integer.parseInt(cin_up.getText()),"pas d'image", role);
        us.insert(u);
        display();
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ajout");
		alert.setHeaderText("");
		alert.setContentText("utilisateur ajouté avec succés");
                alert.showAndWait();
                clearFields();
                 }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("mot de passe invalide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une mot de passe valide");
           alert.showAndWait();
                 }
             }else{
                 Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("numéro de carte identité non valide");
                 alert.setHeaderText(null);
                 alert.setContentText("Vous devez entrez un numéro de carte identité valide");
                 alert.showAndWait();
             }
         }else{
                     Alert alert= new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("numéro de téléphone non valide");
                     alert.setHeaderText(null);
                     alert.setContentText("Vous devez entrez un numéro de téléphone valide");
                     alert.showAndWait();
         }
       }else{
               Alert alert= new Alert(Alert.AlertType.ERROR);
               alert.setTitle("e-mail non valide");
               alert.setHeaderText(null);
               alert.setContentText("Vous devez entrez une adresse e-mail valide");
               alert.showAndWait(); 
       }
       }
            }

    @FXML
    private void switch_gestion_roles(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DisplayRole.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void displayById(ActionEvent event) {
 
        if(id.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("champ vide ");
		alert.setHeaderText("");
		alert.setContentText("vous devez remplir le champ");
                alert.showAndWait();
                display();
        }else{
             int id_search= Integer.parseInt(id.getText());
        User u= us.readByID(id_search);
       
        if(u != null){
            tableUsers.getItems().clear();
            id_user.setText(String.valueOf(u.getId_user()));
            nom.setText(u.getNom());
            prenom.setText(u.getUsername());
            email.setText(u.getEmail());
            mdp.setText(u.getMdp());
            num_tel.setText(String.valueOf(u.getNum_tel()));
            cin.setText(String.valueOf(u.getCin()));
            image.setText(String.valueOf(u.getImage()));
            role.setText(String.valueOf(u.getRole()));
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("pas de utilisateur avec cet id");
                alert.showAndWait();
                display();
        }
    
        }
 
       
    }
    private void clearFields(){
    nom_up.setText("");
    prenom_up.setText("");
    username_up.setText("");
    email_up.setText("");
    mdp_up.setText("");
    num_tel_up.setText("");
    cin_up.setText(""); 
    combo_role.setValue(null);
}   

}

 
   


