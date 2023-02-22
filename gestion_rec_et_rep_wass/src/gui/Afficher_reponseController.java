/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import java.net.URL;
import static java.nio.file.Files.delete;
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
import services.ReponseService;
import services.ReclamationService;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Afficher_reponseController implements Initializable {

    @FXML
    private TableView<Reponse> reponse;
    @FXML
    private TableColumn<Reponse, Integer> idreclamation;
    @FXML
    private TableColumn<Reclamation, String> reclamationtext;
    @FXML
    private TableColumn<Reponse, Button> repondre;
    @FXML
    private TableColumn<Reponse, Button> update;
    @FXML
    private TableColumn<Reponse, Button> delete;
    @FXML
    private TableColumn<Reponse, String> date;
    @FXML
    private TableColumn<Reponse,Integer > idreponse;
    @FXML
    private TableColumn<Reponse, String> messages;
    
     ReponseService Rep = new ReponseService();
     ReclamationService Rs = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
         try {
           
            List<Reponse> reponset = Rep.recuperer();
            ObservableList<Reponse> olc = FXCollections.observableArrayList(reponset);
            List<Reclamation> reclamationet = Rs.recuperer();
            ObservableList<Reclamation> ol = FXCollections.observableArrayList(reclamationet);
           
            
             idreclamation.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_reclamation"));
             reclamationtext.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("message_rec"));
           
            
            
            date.setCellValueFactory(new PropertyValueFactory<Reponse, String>("date_rep"));
           idreponse.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_reponse"));
           messages.setCellValueFactory(new PropertyValueFactory<Reponse, String>("message_rep"));
           this.repondre();
            this.delete();
            this.update();
     
       
          reponse.setItems(olc);
       
          
     
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
                                if (Rep.supprimer(reponse.getItems().get(getIndex())))
                                        {
                                           
                                   
                                    reponse.getItems().remove(getIndex());
                                    reponse.refresh();

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
                        Button b = new Button("update");
                        b.setOnAction((event) -> {
                            try {
                                if (Rep.supprimer(reponse.getItems().get(getIndex())))
                                        {
                                           
                                   
                                    reponse.getItems().remove(getIndex());
                                    reponse.refresh();

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
       
       
       
       
       
         public void repondre() {
        repondre.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("repondre");
                        b.setOnAction((event) -> {
                            try {
                                if (Rep.supprimer(reponse.getItems().get(getIndex())))
                                        {
                                           
                                   
                                    reponse.getItems().remove(getIndex());
                                    reponse.refresh();

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
     
     
     
     
     
     
}
