/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entity.MoyTran;
import entity.Station;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
//import org.webjars.npm.leaflet.L;
//import org.
import javafx.stage.Stage;
import org.openstreetmap.josm.gui.MapView;
import service.MoyTranService;
import service.StationService;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


/**
 * FXML Controller class
 *
 * @author User
 */

public class CRUDSTATIONController implements  Initializable  {
    private Stage stage;
    private Scene scene;
    private Parent root1;



// read the output of the PHP process
@FXML
private MapView mapView;

private Map map;
@FXML
private JMapViewer mapViewer;
    @FXML
    private TextField txtlonalt;
    @FXML
    private ComboBox<Integer> txtmoy;
    @FXML
    private TableView<Station> tab_station;
    @FXML
    private TableColumn<Station, Integer> idstation;
    @FXML
    private TableColumn<Station, String> lon_alt;
    @FXML
    private TableColumn<Station, Integer> idmoy;
    @FXML
    private Button btnwitch;
    @FXML
    private TextField txtch;
    @FXML
    private Button btnMap;
    @FXML
    private Button btnmark;
     @FXML
    private WebView webView;
    
    private WebEngine webEngine;

    public CRUDSTATIONController() {
 
    }
    /**
     * Initializes the controller class.
     */
     @FXML
    private void UpdateTable() {
        List<Station> list=new ArrayList<>();
        
        StationService ss=new StationService();
        if (txtch.getText().length() == 0)
        list=ss.getAll();
        else{
        list.add(ss.getOneById(Integer.parseInt(txtch.getText())));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Recherche avec succés");
                alert.showAndWait();
        }
        
        ObservableList<Station> obs=FXCollections.observableArrayList(list);
        idstation.setCellValueFactory(new PropertyValueFactory<Station ,Integer>("id_station"));
        lon_alt.setCellValueFactory(new PropertyValueFactory<Station ,String>("lang_alt"));
        idmoy.setCellValueFactory(new PropertyValueFactory<Station ,Integer>("id_moyen_transport"));

       
        tab_station.setItems(obs);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        MoyTranService m = new MoyTranService();
        ObservableList<MoyTran> MoyTrans = FXCollections.observableArrayList(m.readAll());
        List<Integer> id_moy = MoyTrans.stream().map(MoyTran::getId_moy).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_moy);
        txtmoy.setItems(observableIds); //cle etragere
        UpdateTable();
    }    

    @FXML
    private void ajouterstation(ActionEvent event) {
         String Dep=txtlonalt.getText();

    if(Dep.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else{
   Station t = new Station(String.valueOf(txtlonalt.getText()),txtmoy.getValue());
        StationService s =new StationService();
        s.ajouter(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Insertion avec succés");
                alert.showAndWait();
        UpdateTable();
    }
    }

    @FXML
private void modifierstation(ActionEvent event) {
    if (txtlonalt.getText().isEmpty() || txtmoy.getValue() == null) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Aucun champ vide n'est accepté!", ButtonType.OK);
        a.showAndWait();
    } else {
        StationService sr = new StationService();
        Station r;

        // Get the selected row from the TableView
        Station selectedStation = tab_station.getSelectionModel().getSelectedItem();

        // Get the id_station value from the selected row
        int id_station = selectedStation.getId_station();

        r = new Station(id_station, txtlonalt.getText(), txtmoy.getValue());
        sr.modifier(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "station modifiée", ButtonType.OK);
        alert.show();
        UpdateTable();
        
    }
}


    @FXML
    private void supprimerstation(ActionEvent event) {
               StationService is=new StationService();
        Station selected_it =  tab_station.getSelectionModel().getSelectedItem();
        is.delete(selected_it.getId_station());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppression avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void switchtmp(ActionEvent event) throws IOException {
         root1 = FXMLLoader.load(getClass().getResource("CRUDCOM.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openMap(ActionEvent event) {
       WebView mapWebView = new WebView();
    WebEngine mapEngine = mapWebView.getEngine();
    //mapEngine.load(getClass().getResource("map.html").toExternalForm());
    mapEngine.load("http://localhost/PhpProject3/getstations.php");
    Scene mapScene = new Scene(mapWebView);
    Stage mapStage = new Stage();
    mapStage.setScene(mapScene);
    mapStage.show();
    }

    @FXML
    private void generateQRCode(ActionEvent event) throws WriterException {
          // Get the selected station from the table
    Station selectedStation = tab_station.getSelectionModel().getSelectedItem();
    
    // Create a string containing the station information
    String stationInfo =selectedStation.getLang_alt();
    
    // Generate a QR code from the station information
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

BitMatrix bitMatrix = qrCodeWriter.encode(stationInfo, BarcodeFormat.QR_CODE, 200, 200);
    
    // Convert the QR code to an image
    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
    
    // Display the image in a new window
    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(image, null));
    Stage stage = new Stage();
    stage.setScene(new Scene(new BorderPane(imageView), 300, 300));
    stage.show();
    }
 
@FXML
private void ajoutmark(ActionEvent event) {
   Station station = tab_station.getSelectionModel().getSelectedItem();
if (station != null && mapViewer != null) {
String[] latLng = station.getLang_alt().split(",");
if (latLng.length == 2) {
double latitude = Double.parseDouble(latLng[0]);
double longitude = Double.parseDouble(latLng[1]);
   MapMarker marker = new MapMarkerDot(latitude, longitude);
        mapViewer.addMapMarker(marker);
        if (!webView.isVisible()) {
    webView.setVisible(true);
}
    }
}
}

   
    }
    

