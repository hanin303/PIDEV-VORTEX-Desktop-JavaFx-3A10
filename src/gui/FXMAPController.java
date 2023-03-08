package gui;

import entity.Station;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.nashorn.api.scripting.JSObject;

/**
 * FXML Controller class
 *
 */
public class FXMAPController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TableView<Station> tab_station;

    private WebEngine webEngine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
       // webEngine.load(getClass().getResource("map2.html").toExternalForm());
        webEngine.load("http://localhost/PhpProject3/getstations.php");

        // Listen for changes in the table view selection
        tab_station.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Station>() {
            @Override
            public void changed(ObservableValue<? extends Station> observable, Station oldValue, Station newValue) {
                if (newValue != null) {
                    // Get the latitude and longitude from the selected station
                    String[] coordinates = newValue.getLang_alt().split(",");
                    Double lat = Double.parseDouble(coordinates[0]);
                    Double lon = Double.parseDouble(coordinates[1]);

                    // Call the JavaScript function to add a marker to the map
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.call("addMarker", lat, lon);

                    // Clear the selection to avoid adding the same marker multiple times
                    tab_station.getSelectionModel().clearSelection();
                }
            }
        });
    }
}
