/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.Ligne;
import entity.MoyTran;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
import service.LigneService;
import service.MoyTranService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CRUDLIGNEController implements Initializable {

    
    private Stage stage;
    private Scene scene;
    private Parent root1;
    
    ObservableList<LigneService>  List = FXCollections.observableArrayList();
    private Connection conn;
    
    @FXML
    private TableView<Ligne> tablig;
    @FXML
    private TextField txtn;
    @FXML
    private ComboBox<String> txttp;
    @FXML
    private TableColumn<Ligne, String> nom_li;
    @FXML
    private TableColumn<Ligne, String> type_li;
    @FXML
    private TableColumn<Ligne, Integer> id_li;
    @FXML
    private TextField txtche;
    private User u;
    UserService us= new UserService();

    /**
     * Initializes the controller class.
     */
    public void getUser(User u){
        this.u=u;
    }
    @FXML
    public void UpdateTable(){
          LigneService ls=new LigneService();
          List<Ligne> list=new ArrayList<>();
        if (txtche.getText().length() == 0)
            list=ls.readAll();
        else{
        list.add(ls.readByID(Integer.parseInt(txtche.getText())));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Recherche avec succés");
                alert.showAndWait();
        }
      
       
        //list=ls.readAll();
        ObservableList<Ligne> obs=FXCollections.observableArrayList(list);
        id_li.setCellValueFactory(new PropertyValueFactory<Ligne ,Integer>("id_ligne"));
        nom_li.setCellValueFactory(new PropertyValueFactory<Ligne ,String>("nom_ligne"));
        type_li.setCellValueFactory(new PropertyValueFactory<Ligne ,String>("type_ligne"));
       
        
        tablig.setItems(obs);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("lignetravail","lignescolaire","ligneusi");
        txttp.setItems(list);
        UpdateTable();
    }    

    @FXML
    private void add_lig(ActionEvent event) {
        Ligne l = new Ligne(txtn.getText(),txttp.getValue());
        LigneService ls =new LigneService();
        ls.insert(l);
     
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
                UpdateTable();
    }

    @FXML
    private void delete_lig(ActionEvent event) {
        LigneService ls=new LigneService();
        Ligne selected_it =  tablig.getSelectionModel().getSelectedItem();
        ls.delete(selected_it.getId_ligne());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppression avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void update_lig(ActionEvent event) {
         List<Object> list = new ArrayList<>(Arrays.asList(txtn.getText(), txttp.getValue()));
        LigneService ls=new LigneService();
        Ligne selected_trajet =  tablig.getSelectionModel().getSelectedItem();
        ls.update(list,selected_trajet.getId_ligne());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void limit3(KeyEvent event) {
         if (txtn.getText().length() >= 9) {
    event.consume();
    }else if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        txtn.setStyle("-fx-border-color: red");
    }else{
        txtn.setStyle("-fx-border-color: green");
    }
    }

    @FXML
    private void switchtmoy(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDMOY.fxml"));
        Parent root1 = loader.load();
//        root1 = FXMLLoader.load(getClass().getResource("CRUDMOY.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
        CRUDMOYController controller = loader.getController();
        controller.getUser(u);
    }

    @FXML
    private void stat(ActionEvent event) {
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BarChart.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        if(u.getRole().getId_role()==1){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageAdmin.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageAdminController controller = loader.getController();
        controller.setFields(u);
            
        }else{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageAdminStation.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageAdminStationController controller = loader.getController();
        controller.setFields(u);
    }
    }
    
}
