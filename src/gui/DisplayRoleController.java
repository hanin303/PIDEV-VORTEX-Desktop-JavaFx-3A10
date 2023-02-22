/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Role;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayRole();
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
    
    public void modifierRole(){
        String newNom= nom_up.getText();
        if(newNom.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez entrer un role"); 
             alert.showAndWait();
        }else{
        List<Object> list= new ArrayList<>(Arrays.asList(nom_up.getText()));
        Role r= tableRole.getSelectionModel().getSelectedItem();
        rs.update(list, r.getId_role());
        displayRole();
        }
        
    }
    public void ajouterRole(){
        String newId=id_role1.getText();
        String newNom=nom1.getText();
        if(newId.isEmpty()||newNom.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
        }else{
        Role r = new Role(Integer.parseInt(id_role1.getText()),nom1.getText());
        rs.insert(r);
        displayRole();}
        
    }
    public void supprimerRole(){
        Role r= tableRole.getSelectionModel().getSelectedItem();
        rs.delete(r.getId_role());
        displayRole();
        
    }
    

    
}
