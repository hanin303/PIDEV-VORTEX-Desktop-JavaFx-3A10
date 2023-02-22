/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reservation;
import entity.Ticket;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.ReservationService;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author hanin
 */
public class TicketController implements Initializable {

    @FXML
    private ComboBox<String> txtstatus;
    @FXML
    private TextField txtprix;
    @FXML
    private ComboBox<Integer> txtinfo;
    @FXML
    private TableView<Ticket> tvTicket;
    @FXML
    private TableColumn<Ticket, Integer> colid;
    @FXML
    private TableColumn<Ticket, String> colstatus;
    @FXML
    private TableColumn<Ticket, String> colprix;
    @FXML
    private TableColumn<Ticket, Integer> colinfo;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    private Button btnGo;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
        public void UpdateTable(){
        List<Ticket> list=new ArrayList<>();
        TicketService ts = new TicketService();
        list=ts.readAll();
        
        ObservableList<Ticket> obs=FXCollections.observableArrayList(list);
        colid.setCellValueFactory(new PropertyValueFactory<Ticket ,Integer>("id_t"));
        colstatus.setCellValueFactory(new PropertyValueFactory<Ticket ,String>("status"));
        colprix.setCellValueFactory(new PropertyValueFactory<Ticket ,String>("prix"));
        colinfo.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("reservation"));
        tvTicket.setItems(obs);
     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationService rs = new ReservationService();
        ObservableList<Reservation> reservations = FXCollections.observableArrayList(rs.readAll());
        List<Integer> id_reservation = reservations.stream().map(Reservation::getId_reservation).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_reservation);
        txtinfo.setItems(observableIds);
        
        ObservableList<String> status = FXCollections.observableArrayList("payer", "non_payer");
        txtstatus.setItems(status);
        txtstatus.setValue("non_payer");    
        UpdateTable();
      
   } 

    @FXML
    private void SupprimerTicket(ActionEvent event) {
    TicketService rs=new TicketService();
    Ticket selected_it = tvTicket.getSelectionModel().getSelectedItem();
    rs.delete(selected_it.getId_t());
       Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Voulez-vous vraiment supprimer ce ticket ?");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void ModifierTicket(ActionEvent event) {
    List<Object> list = new ArrayList<>(Arrays.asList(txtstatus.getValue(), txtprix.getText(), txtinfo.getValue()));
    TicketService rs=new TicketService();
    Ticket selected_it = tvTicket.getSelectionModel().getSelectedItem();
    rs.update(list,selected_it.getId_t());
     Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
    UpdateTable();
   
        
    }

    @FXML
    private void AjouterTicket(ActionEvent event) {
    ReservationService rs=new ReservationService();
    Ticket res = new Ticket(txtstatus.getValue(), txtprix.getText(),rs.readByID(txtinfo.getValue())); 
    System.out.println(res);
    TicketService ts = new TicketService();
    ts.insert(res);
    
       Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Ticket ajouter avec succés");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void Switchscreenreservation(ActionEvent event)throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void controlesaisieprix(KeyEvent event) {
        
       if (event.getCharacter().matches("[^\\e\t\r\\d+$]")){
           event.consume();
           txtprix.setStyle("-fx-border-color:red");
           event.consume();
       }else
           
           txtprix.setStyle("-fx-border-color:green");
       }
    }

    
