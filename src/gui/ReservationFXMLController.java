/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Iteneraire;
import entity.Reservation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.ItineraireService;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author hanin
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private ComboBox<Integer> txtitineraire;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtheuredepart;
    @FXML
    private TextField txtheurearrive;
    @FXML
    private TextField txttype;
    @FXML
    private ComboBox<Integer> txttransport;
    @FXML
    private ComboBox<String> txtstatus;
    @FXML
    private ComboBox<Integer> txtusr;
    @FXML
    private TableView<Reservation> tvReservation;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, LocalDate> colDate;
    @FXML
    private TableColumn<Reservation, String> colHdepart;
    @FXML
    private TableColumn<Reservation, String> colHarrive;
    @FXML
    private TableColumn<Reservation, Integer> colit;
    @FXML
    private TableColumn<Reservation, Integer> colTransport;
    @FXML
    private TableColumn<Reservation, String> colType;
    @FXML
    private TableColumn<Reservation, String> colStatus;
    @FXML
    private TableColumn<Reservation, Integer> colusr;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnGo;
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Initializes the controller class.
     */
    
    
    
    public void UpdateTable(){
        List<Reservation> list=new ArrayList<>();
        ReservationService rs = new ReservationService();
        list=rs.readAll();
        
        ObservableList<Reservation> obs=FXCollections.observableArrayList(list);
        colid.setCellValueFactory(new PropertyValueFactory<Reservation ,Integer>("id_reservation"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reservation ,LocalDate>("date_reservation"));
        colHdepart.setCellValueFactory(new PropertyValueFactory<Reservation ,String>("heure_depart"));
        colHarrive.setCellValueFactory(new PropertyValueFactory<Reservation ,String>("heure_arrive"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation,String>("status"));
        colusr.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_user"));
        colTransport.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_moy"));
        colit.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("iteneraire"));
        colType.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type_ticket"));
        tvReservation.setItems(obs);
     
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        ItineraireService is = new ItineraireService();
        ObservableList<Iteneraire> itineraires = FXCollections.observableArrayList(is.readAll());
        List<Integer> id_itineraire = itineraires.stream().map(Iteneraire::getId).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_itineraire);
        txtitineraire.setItems(observableIds);
      
         ReservationService rss = new ReservationService();
         ObservableList<Reservation> resList = FXCollections.observableArrayList(rss.readAll());
         List<Integer> id_moy_trans = resList.stream().map(Reservation::getId_moy).collect(Collectors.toList());
         ObservableList<Integer> observableIds_moy = FXCollections.observableList(id_moy_trans);
         txttransport.setItems(observableIds_moy);
         
         
         List<Integer> id_user = resList.stream().map(Reservation::getId_user).collect(Collectors.toList());
         ObservableList<Integer> observableIds_user = FXCollections.observableList(id_user);
         txtusr.setItems(observableIds_user);
        
        
        ObservableList<String> status = FXCollections.observableArrayList("confirme", "en_attente","annule");
        txtstatus.setItems(status);
        txtstatus.setValue("en_attente");
        
         UpdateTable();
    }
        

   @FXML
private void AjouterReservation(ActionEvent event) {
    ItineraireService ts=new ItineraireService();
    Reservation res = new Reservation(txtstatus.getValue(),txtdate.getValue(), txtheuredepart.getText(), txtheurearrive.getText(), txtusr.getValue(),txttransport.getValue(),ts.readByID(txtitineraire.getValue()),txttype.getText()); 
    System.out.println(res);
    ReservationService rs = new ReservationService();
    rs.insert(res);
   
    
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Reservation ajouter avec succés");
                alert.showAndWait();
    UpdateTable();
}

    @FXML
    private void ModifierReservation(ActionEvent event) {
    List<Object> list = new ArrayList<>(Arrays.asList(txtstatus.getValue(),txtheuredepart.getText(),txtheurearrive.getText(),txtusr.getValue(),txttransport.getValue(),txtitineraire.getValue(),txttype.getText()));
    //List<Object> list = new ArrayList<>(Arrays.asList(txtstatus.getValue(),txtdate.getValue(),txtheuredepart.getText(),txtheurearrive.getText(),txtusr.getValue(),txttransport.getValue(),txtitineraire.getValue(),txttype.getText()));
    ReservationService rs=new ReservationService();
    Reservation selected_it = tvReservation.getSelectionModel().getSelectedItem();
    rs.update(list,selected_it.getId_reservation());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) {
    ReservationService rs=new ReservationService();
    Reservation selected_it = tvReservation.getSelectionModel().getSelectedItem();
    rs.delete(selected_it.getId_reservation());
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Voulez-vous vraiment supprimer cette Réservation ? ");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void switchscreenticket(ActionEvent event)throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void ControleSaisieDate(KeyEvent event) {
        
        txtdate.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == null || newValue.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La date sélectionnée est invalide !!");
            alert.showAndWait();
            txtdate.setValue(oldValue);
        }
         });
        
    }

    @FXML
    private void ControleSaisieHeure(KeyEvent event) {
        txtheuredepart.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.trim().isEmpty()) {
            txtheuredepart.setStyle("-fx-border-color: red ;");
            txtheuredepart.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le saisie de l'heure depart est obligatoire !!");
            alert.showAndWait();
        } else {
            txtheuredepart.setStyle("-fx-border-color: transparent ;");
        }
        });
                }
    

   
}


