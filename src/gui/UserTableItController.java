/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Iteneraire;
import entity.Trajet;
import entity.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class UserTableItController implements Initializable {
     private Stage stage;
    private Scene scene;
    private Parent root;
   @FXML
    private TableView<Iteneraire> table_Itineraire;
    @FXML
    private TableColumn<Iteneraire, Integer> id_it;
    @FXML
    private TableColumn<Iteneraire, String> pts_depart_it;
    @FXML
    private TableColumn<Iteneraire, String> pts_arrive_it;
    @FXML
    private TableColumn<Iteneraire,String> id_it_trajet;
    @FXML
    private Button supprimer_button;
    @FXML
    private Button modifier_button;
    @FXML
    private TextField pts_depart_text;
    @FXML
    private TextField pts_depart_arrive;
    @FXML
    private ComboBox<Integer> combo_trajet;
    private User u;

    /**
     * Initializes the controller class.
     */
     public void UpdateTable(){
        List<Iteneraire> list=new ArrayList<>();
        List<Iteneraire> list2=new ArrayList<>();
        List<Iteneraire> list3=new ArrayList<>();
        ItineraireService is = new ItineraireService();
        list=is.readAll();
        list3 = list.stream().map(i->{String fromto = "no";
 
                return new Iteneraire(i.getId(),i.getPts_depart(),i.getPts_arrivee(),fromto);}).
                collect(Collectors.toList());
         
        
       
        
        list2 = list.stream().map(it->new Iteneraire(it.getId(),it.getPts_depart(),
        it.getPts_arrivee())).collect(Collectors.toList());
        System.out.println(list3);
        
        
        
                
        ObservableList<Iteneraire> obs=FXCollections.observableArrayList(list3);
//        List<Trajet> trajet_list = obs.stream().map(Iteneraire::getTrajet).collect(Collectors.toList());
//        List<Integer> trajet_id_list = trajet_list.stream().map(Trajet::getId).collect(Collectors.toList());
//        ObservableList<Integer> observables_ids_trajet =FXCollections.observableList(trajet_id_list);
        id_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,Integer>("id"));
        pts_depart_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,String>("pts_depart"));
        pts_arrive_it.setCellValueFactory(new PropertyValueFactory<Iteneraire ,String>("pts_arrivee"));
        id_it_trajet.setCellValueFactory(new PropertyValueFactory<Iteneraire ,String>("fromto"));
        table_Itineraire.setItems(obs);
    }
      public void getUser(User u){
        this.u=u;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UpdateTable();
        TrajetService ts = new TrajetService();
        ObservableList<Trajet> trajets = FXCollections.observableArrayList(ts.readAll());
        List<Integer> id_trajet = trajets.stream().map(Trajet::getId).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_trajet);
        combo_trajet.setItems(observableIds);
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
        List<Object> list = new ArrayList<>(Arrays.asList(pts_depart_text.getText(), 
                pts_depart_arrive.getText(),combo_trajet.getValue()));
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
    private void below8(KeyEvent event) {
        if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            pts_depart_text.setStyle("-fx-border-color: red");
        }
        if(pts_depart_text.getText().length() >= 8){
            event.consume();
            pts_depart_text.setStyle("-fx-border-color: red");
        }
        else{
             pts_depart_text.setStyle("-fx-border-color: blue");
    }
    }
    

    @FXML
    private void below82(KeyEvent event) {
        if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            pts_depart_arrive.setStyle("-fx-border-color: red");
        }
        
        if(pts_depart_arrive.getText().length() >= 8){
            event.consume();
            pts_depart_arrive.setStyle("-fx-border-color: red");
        }
        else{
             pts_depart_arrive.setStyle("-fx-border-color: blue");
    }
        
    }
     @FXML
    private void SwitchWindow(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
        Parent root = loader.load(); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        UserInterfaceController controller = loader.getController();
        controller.getUser(u);
    }

    
}
