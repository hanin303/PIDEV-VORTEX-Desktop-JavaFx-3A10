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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entity.Trajet;
import entity.Iteneraire;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.ItineraireService;
import service.TrajetService;
/**
 * FXML Controller class
 *
 * @author kalee
 */
public class ItineraireController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField text_pts_depart_it;
    @FXML
    private TextField text_pts_arrive_it;
    @FXML
    private Button button_ajouter_it;
    @FXML
    private Button button_supprimer_it;
    @FXML
    private Button button_modifier_it;
    @FXML
    private TableView<Iteneraire> table_Itineraire;
    @FXML
    private TableColumn<Iteneraire, Integer> id_it;
    @FXML
    private TableColumn<Iteneraire, String> pts_depart_it;
    @FXML
    private TableColumn<Iteneraire, String> pts_arrive_it;
    @FXML
    private TableColumn<Iteneraire,Integer> id_it_trajet;
    @FXML
    private ComboBox<Integer> combo_id_trajet;
    @FXML
    private Button button_gestion_t;

    /**
     * Initializes the controller class.
     */
    public void UpdateTable(){
        List<Iteneraire> list=new ArrayList<>();
        
        ItineraireService is = new ItineraireService();
        list=is.readAll();
        
        
                
        ObservableList<Iteneraire> obs=FXCollections.observableArrayList(list);
//        List<Trajet> trajet_list = obs.stream().map(Iteneraire::getTrajet).collect(Collectors.toList());
//        List<Integer> trajet_id_list = trajet_list.stream().map(Trajet::getId).collect(Collectors.toList());
//        ObservableList<Integer> observables_ids_trajet =FXCollections.observableList(trajet_id_list);
        id_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,Integer>("id"));
       
        pts_depart_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,String>("pts_depart"));
        pts_arrive_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,String>("pts_arrivee"));
        id_it_trajet.setCellValueFactory(new PropertyValueFactory<Iteneraire ,Integer>("trajet"));
        table_Itineraire.setItems(obs);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ItineraireService is = new ItineraireService();
        TrajetService ts = new TrajetService();
        ObservableList<Trajet> trajets = FXCollections.observableArrayList(ts.readAll());
        List<Integer> id_trajet = trajets.stream().map(Trajet::getId).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_trajet);
        combo_id_trajet.setItems(observableIds);
        UpdateTable();
    }
    @FXML
    private void AjouterItineraire(ActionEvent event) {
        TrajetService ts = new TrajetService();
        ItineraireService is = new ItineraireService();
        Iteneraire i=new Iteneraire(text_pts_depart_it.getText(), 
                text_pts_arrive_it.getText(),ts.readByID(combo_id_trajet.getValue()));
        is.insert(i);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Ajout avec succés");
                alert.showAndWait();
        UpdateTable();
    
    }

    @FXML
    private void SupprimerItineraire(ActionEvent event) {
    ItineraireService is=new ItineraireService();
        Iteneraire selected_it =  table_Itineraire.getSelectionModel().getSelectedItem();
        is.delete(selected_it.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppresion avec succés");
                alert.showAndWait();
        UpdateTable();
    }
    @FXML
    private void ModifierItineraire(ActionEvent event) {
        List<Object> list = new ArrayList<>(Arrays.asList(text_pts_depart_it.getText(), 
                text_pts_arrive_it.getText(),combo_id_trajet.getValue()));
        ItineraireService is=new ItineraireService();
        Iteneraire selected_it =  table_Itineraire.getSelectionModel().getSelectedItem();
        is.update(list,selected_it.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void SwitchWindowTr(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("Trajet.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void below8(KeyEvent event) {
        if(text_pts_depart_it.getText().length() >= 8){
            event.consume();
            text_pts_depart_it.setStyle("-fx-border-color: red");
        }
        else{
             text_pts_depart_it.setStyle("-fx-border-color: blue");
    }
    }
    

    @FXML
    private void below82(KeyEvent event) {
        if(text_pts_arrive_it.getText().length() >= 8){
            event.consume();
            text_pts_arrive_it.setStyle("-fx-border-color: red");
        }
        else{
             text_pts_arrive_it.setStyle("-fx-border-color: blue");
    }
        
    }

    
    
    
}
