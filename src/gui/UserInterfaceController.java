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
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import service.ItineraireService;
import service.TrajetService;

/**
 * FXML Controller class
 *
 * @author kalee
 */
public class UserInterfaceController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField pts_depart_text;
    @FXML
    private TextField pts_depart_arrive;
    @FXML
    private WebView mapview;
    @FXML
    private TextField search_field;
    @FXML
    private Button valider_button;
    @FXML
    private ComboBox<Integer> combo_trajet;
    @FXML
    private Button valider_button1;
    private User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapview.getEngine().load(getClass().getResource("map.html").toExternalForm());
         search_field.setOnAction(event -> {
    String query = search_field.getText();
    mapview.getEngine().executeScript("search('" + query + "')");
         });
    TrajetService ts = new TrajetService();
        ObservableList<Trajet> trajets = FXCollections.observableArrayList(ts.readAll());
        List<Integer> id_trajet = trajets.stream().map(Trajet::getId).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_trajet);
        combo_trajet.setItems(observableIds);
      
        
}
    
    public void getUser(User u){
        this.u=u;
    }
    @FXML
    private void AjouterItineraire(ActionEvent event) {
        TrajetService ts = new TrajetService();
        ItineraireService is = new ItineraireService();
        Iteneraire i=new Iteneraire(pts_depart_text.getText(), 
                pts_depart_arrive.getText(),ts.readByID(combo_trajet.getValue()));
        is.insert(i);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Ajout avec succés");
                alert.showAndWait();
        
    
    }
    @FXML
    private void SwitchWindow(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserTableIt.fxml"));
        Parent root = loader.load(); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        UserTableItController controller = loader.getController();
        controller.getUser(u);
    }

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageCLIENT.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageClientController controller = loader.getController();
        controller.setFields(u);
    }

    
}                
  