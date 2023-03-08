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
import entity.User;
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
import service.ExcelExporter;

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
    @FXML
    private TextField textsearch;
    @FXML
    private Button btnsearch;
    @FXML
    private Button button_window_it1;
    private User u;

    /**
     * Initializes the controller class.
     */@FXML
 public void UpdateTable(){
        List<Trajet> list=new ArrayList<>();
        TrajetService ts=new TrajetService();
        if(textsearch.getText().length()==0)
        list=ts.readAll();
        else
        list.add(ts.readByID(Integer.parseInt(textsearch.getText())));
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
    public void getUser(User u){
        this.u=u;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Itineraire.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ItineraireController controller = loader.getController();
        controller.getUser(u);
        
;    }

    @FXML
    private void limit2eight(KeyEvent event) {
        if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            text_pts_depart.setStyle("-fx-border-color: red");
        }
        else{
             text_pts_depart.setStyle("-fx-border-color: blue");
    }
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
        if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            text_pts_arrive.setStyle("-fx-border-color: red");
        }
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
    // your list of objects
    @FXML
    public void exportExcel()throws IOException{
        ExcelExporter ex = new ExcelExporter();
        TrajetService ts = new TrajetService();
        ex.export(ts.readAll(), "tableTrajet.xlsx");
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
