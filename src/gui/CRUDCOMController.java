/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Commune;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CommuneService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CRUDCOMController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root1;
    @FXML
    private TextField txtlonalt;
    @FXML
    private Button btnwitch;
    @FXML
    private TextField txtch;
    @FXML
    private TextField txtnom;
    private TableView<Commune> tab_station;
    @FXML
    private TableColumn<Commune, Integer> idcommune;
    @FXML
    private TableColumn<Commune, String> idnom;
    @FXML
    private TableColumn<Commune, String> lon_alt;
    @FXML
    private TableView<Commune> tab_commune;
    private User u;

    /**
     * Initializes the controller class.
     */
    
    public void getUser(User u){
        this.u=u;
    }
         @FXML
    private void UpdateTable() {
        List<Commune> list=new ArrayList<>();
        
        CommuneService cs=new CommuneService();
        if (txtch.getText().length() == 0)
        list=cs.getAll();
        else{
        list.add(cs.getOneById(Integer.parseInt(txtch.getText())));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Recherche avec succés");
                alert.showAndWait();
        }
        
        ObservableList<Commune> obs=FXCollections.observableArrayList(list);
        idcommune.setCellValueFactory(new PropertyValueFactory<Commune ,Integer>("id_commune"));
        idnom.setCellValueFactory(new PropertyValueFactory<Commune ,String>("nom_commune"));
        lon_alt.setCellValueFactory(new PropertyValueFactory<Commune ,String>("long_alt"));

       
        tab_commune.setItems(obs);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateTable();
    }    

    @FXML
    private void ajoutercommune(ActionEvent event) {
         String Dep=txtnom.getText();
         String Dep1=txtlonalt.getText();

    if(Dep.isEmpty()||Dep1.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else{
   Commune t = new Commune(String.valueOf(txtnom.getText()),txtlonalt.getText());
        CommuneService c =new CommuneService();
        c.ajouter(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Insertion avec succés");
                alert.showAndWait();
        UpdateTable();
    }
    }

    @FXML
    private void modifiercommune(ActionEvent event) {
          if (txtnom.getText().isEmpty() || txtlonalt.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Aucun champ vide n'est accepté!", ButtonType.OK);
        a.showAndWait();
    } else {
        CommuneService cr = new CommuneService();
        Commune r;

        // Get the selected row from the TableView
        Commune selectedCommune = tab_commune.getSelectionModel().getSelectedItem();

        // Get the id_station value from the selected row
        int id_commune = selectedCommune.getId_commune();

        r = new Commune(id_commune, txtnom.getText(), txtlonalt.getText());
        cr.modifier(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Commune modifiée", ButtonType.OK);
        alert.show();
        UpdateTable();
        
    }
    }

    @FXML
    private void supprimercommune(ActionEvent event) {
                    CommuneService is=new CommuneService();
        Commune selected_it =  tab_commune.getSelectionModel().getSelectedItem();
        is.delete(selected_it.getId_commune());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Suppression avec succés");
                alert.showAndWait();
        UpdateTable();
    }

    @FXML
    private void switchtmp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDSTATION.fxml"));
        Parent root1 = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
        CRUDSTATIONController controller = loader.getController();
        controller.getUser(u);
    }

    @FXML
    private void BackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageAdmin.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageAdminController controller = loader.getController();
        controller.setFields(u);
        
    }
 
    
}
