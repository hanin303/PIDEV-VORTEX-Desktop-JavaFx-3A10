/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ReclaStatController implements Initializable {

    @FXML
    private PieChart chartfor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.Recla();
    }    
    
     @FXML
    private void Recla() {
         ReclamationService serv = new ReclamationService();
        Integer s1 = serv.Stats("retard","objet");
        Integer s2 = serv.Stats("comportement","objet");
        Integer s3 = serv.Stats("nombre de bus","objet");
        Integer s4 = serv.Stats("Autres","objet");
        System.out.println(s1 +""+s2+""+s3+""+s4+"");


     ObservableList<PieChart.Data> PieChartData
             =FXCollections.observableArrayList(
                     new PieChart.Data("retard",s1),
                     new PieChart.Data("comportement",s2),
                     new PieChart.Data("nombre de bus",s3),
                     new PieChart.Data("Autres",s4)
                   

             );
     chartfor.setData(PieChartData);
     
    }
    
}
