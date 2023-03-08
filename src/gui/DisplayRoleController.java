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
import javafx.scene.control.Button;
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
public class DisplayRoleController implements Initializable {
    
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Role> tableRole;
    @FXML
    private TableColumn<Role,Integer> id_role;
    @FXML 
    private TableColumn<Role,String> nom;

    @FXML
    private TextField id_role1;
    @FXML
    private TextField nom1;
    @FXML
    private TextField nom_up;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
       
    UserService us= new UserService();
    RoleService rs= new RoleService();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User u;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayRole();
    }  
    public void getUser(User u){
        this.u=u;
    }

    public void displayRole(){
        List<Role> list= new ArrayList<>();
        list=rs.readAll();   
        ObservableList<Role> list_role=FXCollections.observableArrayList(list);
        
        id_role.setCellValueFactory(new PropertyValueFactory<Role,Integer>("id_role"));
        nom.setCellValueFactory(new PropertyValueFactory<Role,String>("nom"));
        tableRole.setItems(list_role);
        tableRole.refresh();
    }
    
    @FXML
    public void modifierRole(){
        if(nom_up.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez saisir un role"); 
             alert.showAndWait();
        }else{
        if(rs.readByNom(nom_up.getText())== null){
        List<Object> list= new ArrayList<>(Arrays.asList(nom_up.getText()));
        Role r= tableRole.getSelectionModel().getSelectedItem();
        rs.update(list, r.getId_role());
        displayRole();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("succés");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
                clearFields();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("Role existe déjà");
                 alert.showAndWait();
        }
        }
        
    }
    @FXML
    public void ajouterRole(){
        if(id_role1.getText().isEmpty()||nom1.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
        }else{
           if(rs.readByNom(nom1.getText())==null&&rs.readByID(Integer.parseInt(id_role1.getText()))==null){
           Role r = new Role(Integer.parseInt(id_role1.getText()),nom1.getText());
           rs.insert(r);
           displayRole();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("ajout");
		alert.setHeaderText("");
		alert.setContentText("role ajouté avec succés");
                alert.showAndWait();
                clearFields();
            }else{
                Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("role existe déjà");
                 alert.showAndWait();
            }
        }
        
        
    }
    @FXML
    public void supprimerRole(){
        Role r= tableRole.getSelectionModel().getSelectedItem();
        rs.delete(r.getId_role());
        displayRole();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("suppression");
		alert.setHeaderText("");
		alert.setContentText("role supprimé avec succés");
                alert.showAndWait();
        
    }

    @FXML
    private void switch_gestion_users(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayUser.fxml"));
        Parent root = loader.load();
//         root = FXMLLoader.load(getClass().getResource("DisplayUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        DisplayUserController controller = loader.getController();
        controller.getUser(u);
    }
    private void clearFields(){
    id_role1.setText("");
    nom1.setText("");
   
    
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
