/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.teamdev.jxmaps.swing.MapView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author kalee
 */
public class MapController implements Initializable {
    @FXML
    private AnchorPane mapContainer;
    
    private MapView mapView;
    private WebView mapViewer;
    @FXML
    private WebView map;
    @FXML
    private TextField mapsearch;
    @FXML
    private Button togglebutton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //map.getEngine().load("https://www.openstreetmap.org/#map=13/36.8085/10.1857");
    map.getEngine().load(getClass().getResource("map.html").toExternalForm());
   mapsearch.setOnAction(event -> {
    String query = mapsearch.getText();
    map.getEngine().executeScript("search('" + query + "')");
    
    togglebutton.setOnAction(e -> {
    map.getEngine().executeScript("toggleTransport()");
});
});
  
      
       

      
     
   
    
}}
