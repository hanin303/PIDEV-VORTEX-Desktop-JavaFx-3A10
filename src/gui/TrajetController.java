/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.TrajetService;
import entity.Trajet;
import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kalee
 */
public class TrajetController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField text_pts_depart;
    @FXML
    private TextField text_pts_arrive;
    @FXML
    private Button button_ajouter;
    @FXML
    private TextField text_temps_parcours;
    @FXML
    private Button button_supprimer;
    @FXML
    private Button button_modifier;
    @FXML
    private TableView<Trajet> table_trajet;
    @FXML
    private TableColumn<Trajet, Integer> id_trajet;
    @FXML
    private TableColumn<Trajet, String> temps_parcours;
    @FXML
    private TableColumn<Trajet, String> pts_depart;
    @FXML
    private TableColumn<Trajet, String> pts_arrive;
    @FXML
    private Button button_window_it;

    /**
     * Initializes the controller class.
     */ public void UpdateTable(){
        List<Trajet> list=new ArrayList<>();
        TrajetService ts=new TrajetService();
        list=ts.readAll();
        ObservableList<Trajet> obs=FXCollections.observableArrayList(list);
        id_trajet.setCellValueFactory(new PropertyValueFactory<Trajet ,Integer>("id"));
        temps_parcours.setCellValueFactory(new PropertyValueFactory<Trajet ,String>("temps_parcours"));
        pts_depart.setCellValueFactory(new PropertyValueFactory<Trajet ,String>("pts_depart"));
        pts_arrive.setCellValueFactory(new PropertyValueFactory<Trajet ,String>("pts_arrivee"));
        table_trajet.setItems(obs);
    }
     public void initialize(URL url, ResourceBundle rb) {
        
           UpdateTable();
       
        
        
       
    }    

    @FXML
    private void AjouterTrajet(ActionEvent event) {
        Trajet t=new Trajet(text_temps_parcours.getText(),text_pts_depart.getText(), 
                text_pts_arrive.getText());
        
        TrajetService ts=new TrajetService();
        ts.insert(t);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Ajout avec succés");
                alert.showAndWait();
         UpdateTable();
        
    }
   

    @FXML
    private void SupprimerTrajet(ActionEvent event) {
        TrajetService ts=new TrajetService();
        Trajet selected_trajet =  table_trajet.getSelectionModel().getSelectedItem();
        ts.delete(selected_trajet.getId());
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppresion avec succés");
                alert.showAndWait();
         UpdateTable();
        
    }

    @FXML
    private void ModifierTrajet(ActionEvent event) {
        List<Object> list = new ArrayList<>(Arrays.asList(text_temps_parcours.getText(),text_pts_depart.getText(), 
                text_pts_arrive.getText()));
        TrajetService ts=new TrajetService();
        Trajet selected_trajet =  table_trajet.getSelectionModel().getSelectedItem();
        ts.update(list,selected_trajet.getId());
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void SwitchWindowIt(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("Itineraire.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
;    }

    @FXML
    private void limit2eight(KeyEvent event) {
        if(text_pts_depart.getText().length() >= 8){
            event.consume();
            text_pts_depart.setStyle("-fx-border-color: red");
        }
        else{
             text_pts_depart.setStyle("-fx-border-color: blue");
    }
    }

    @FXML
    private void limit2eight2(KeyEvent event) {
        if(text_pts_arrive.getText().length() >= 8){
            event.consume();
            text_pts_arrive.setStyle("-fx-border-color: red");
        }
        else{
             text_pts_arrive.setStyle("-fx-border-color: blue");
    }}

    @FXML
    private void CheckNumber(KeyEvent event) {
        if(text_temps_parcours.getText().length() >= 2){
            event.consume();
            text_temps_parcours.setStyle("-fx-border-color: red");}
        else{
            text_temps_parcours.setStyle("-fx-border-color: blue");
        }
        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            text_temps_parcours.setStyle("-fx-border-color: red");
        }
        else{
            text_temps_parcours.setStyle("-fx-border-color: blue");
        }
    }

}
