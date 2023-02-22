/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReclamationService;
import entities.Reponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author DELL
 */


public class Afficher_reclamationController implements Initializable {
    
    ReclamationService Rs = new ReclamationService() ;
    @FXML
    private TableView<Reclamation > Reclamation;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> message;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> statut;
    @FXML
    private TableColumn<Reclamation, Button> delete;
    @FXML
    private TableColumn<Reclamation, Button> update;
    @FXML
    private TableColumn<Reponse, String> reponse;
    @FXML
    private Label welcomeLb;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        
          try {
           
            List<Reclamation> reclamationet = Rs.recuperer();
            ObservableList<Reclamation> olc = FXCollections.observableArrayList(reclamationet);
           
            id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_reclamation"));
            message.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("message_rec"));
            type.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type"));
            statut.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statut"));
            this.delete();
            this.update();
     
       
          Reclamation.setItems(olc);
     
    }
            catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

        
        
        
    }    
    
    
    
     public void delete() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Delete");
                        b.setOnAction((event) -> {
                            try {
                                if (Rs.supprimer(Reclamation.getItems().get(getIndex())))
                                        {
                                           
                                   
                                    Reclamation.getItems().remove(getIndex());
                                    Reclamation.refresh();

                                         }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
   
   
   
     public void update() {
   
       
         update.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((event) -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier_reclamation.fxml"));
                           
                            try {
                                  Parent root = loader.load();
                                  welcomeLb.getScene().setRoot(root);
                                 
                                  Reclamation.refresh();
                                 

                                         } catch (IOException ex) {
                                Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           

                           

                        });
                        setGraphic(b);

                    }
                }
            };

           
        });
       
       
       
       
       
       

    }

   
   
   
   
   
   

   
   
    void setData(String Message) {
        welcomeLb.setText("" + Message);
     
    }

    
}











