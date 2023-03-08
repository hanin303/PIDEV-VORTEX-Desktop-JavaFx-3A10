/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import connexionbd.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BarChartController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        loadData();
       
       
        piechart.setData(piechartdata);
        piechart.setTitle("Statistique de Ligne de transport en Tunisie");
    }    
    
     public void loadData() {

        String query = "select COUNT(*) as count ,nom_ligne from ligne GROUP BY nom_ligne "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection cnx = DataSource.getInstance().getCnx();

        try {

            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("nom_ligne"), rs.getInt("count")));
                p.add(rs.getString("nom_ligne"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  
    
}
