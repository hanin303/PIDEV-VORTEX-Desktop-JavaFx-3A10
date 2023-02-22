/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Ligne;
import entity.MoyTran;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.LigneService;
import service.MoyTranService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CRUDMOYController implements Initializable {
    
    ObservableList<MoyTranService>  List = FXCollections.observableArrayList();
    
    private Stage stage;
    private Scene scene;
    private Parent root1;
    
    @FXML
    private TextField txtm;
    @FXML
    private TextField txtcap;
    @FXML
    private ComboBox<String> typev;
    @FXML
    private TextField txtmar;
    @FXML
    private ComboBox<String> txte;
    @FXML
    private ComboBox<Integer> txtl;
    @FXML
    private TableView<MoyTran> tabmoy;
    @FXML
    private TableColumn<MoyTran, Integer> idmoy;
    @FXML
    private TableColumn<MoyTran, Integer> mat;
    @FXML
    private TableColumn<MoyTran, Integer> cap;
    @FXML
    private TableColumn<MoyTran, String> tv;
    @FXML
    private TableColumn<MoyTran, String> marque;
    @FXML
    private TableColumn<MoyTran, String> etat;
    @FXML
    private TableColumn<MoyTran, Integer> li;
    private Connection conn;
    @FXML
    private Button btnwitch;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void UpdateTable(){
        List<MoyTran> list=new ArrayList<>();
        MoyTranService ts=new MoyTranService();
        list=ts.readAll();
        ObservableList<MoyTran> obs=FXCollections.observableArrayList(list);
        idmoy.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("id_moy"));
        mat.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("matricule"));
        cap.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("capacite"));
        tv.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("type_vehicule"));
        marque.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("marque"));
        etat.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("etat"));
        li.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("id_ligne"));
        
        tabmoy.setItems(obs);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("train","bus","métro");
        typev.setItems(list);
         ObservableList<String> list1 = FXCollections.observableArrayList("en_service","hors_service","maintenance");
        txte.setItems(list1);
        LigneService s = new LigneService();
        ObservableList<Ligne> lignes = FXCollections.observableArrayList(s.readAll());
        List<Integer> id_ligne = lignes.stream().map(Ligne::getId_ligne).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_ligne);
        txtl.setItems(observableIds); //cle etragere
        UpdateTable();
      
       
        
     
    }    

    @FXML
    private void add_moy(ActionEvent event) {
        MoyTran t = new MoyTran(Integer.parseInt(txtm.getText()),Integer.parseInt(txtm.getText()),typev.getValue(),txtmar.getText(),txte.getValue(),txtl.getValue());
        MoyTranService s =new MoyTranService();
        s.insert(t);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Insertion avec succés");
                alert.showAndWait();
        UpdateTable();
        
         
    }

    @FXML
    private void delete(ActionEvent event) {
        MoyTranService is=new MoyTranService();
        MoyTran selected_it =  tabmoy.getSelectionModel().getSelectedItem();
        is.delete(selected_it.getId_moy());
         Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppression avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void update(ActionEvent event) {
        List<Object> list = new ArrayList<>(Arrays.asList(Integer.parseInt(txtm.getText()),Integer.parseInt(txtcap.getText()), 
                typev.getValue(),txtmar.getText(),txte.getValue(),txtl.getValue()));
        MoyTranService ts=new MoyTranService();
        MoyTran selected_trajet =  tabmoy.getSelectionModel().getSelectedItem();
        ts.update(list,selected_trajet.getId_moy());
         Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
        UpdateTable();
    }

//    @FXML
//    private void switchtmp(MouseEvent event) throws IOException {
//        root1 = FXMLLoader.load(getClass().getResource("CRUDLIGNE.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root1);
//        stage.setScene(scene);
//        stage.show();
//        
//    }

    @FXML
    private void switchtmp(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(getClass().getResource("CRUDLIGNE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void limit1(KeyEvent event) {
        if (txtm.getText().length() >= 8) {
            
            event.consume();
            txtm.setStyle("-fx-border-color: red");
    }else if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        txtm.setStyle("-fx-border-color: red");
    }else{
        txtm.setStyle("-fx-border-color: green");
    }
    }
    @FXML
    private void limit2(KeyEvent event) {
        if (txtcap.getText().length() >= 3) {
    event.consume();
    txtm.setStyle("-fx-border-color: red");
    }else if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        txtm.setStyle("-fx-border-color: red");
    }else{
        txtm.setStyle("-fx-border-color: green");
    }
    }

    @FXML
    private void switchtmp(MouseEvent event) {
        
    }

    @FXML
    private void limit4(KeyEvent event) {
//        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
//            event.consume();
//        }
    }

   
    
    
}

   
    

