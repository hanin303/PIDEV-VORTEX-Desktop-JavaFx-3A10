/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reclamation;
import entity.Reponse;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.print.DocFlavor;
import service.ReclamationService;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Afficher_reponseController implements Initializable {
    
    ReponseService RS = new ReponseService();
    ReclamationService Rs = new ReclamationService();

    @FXML
    private TableView<Reponse> Reponse;
    @FXML
    private TableColumn<Reponse, Integer> idReponse;
    @FXML
    private TableColumn<Reponse, Integer> idReclamation;
    @FXML
    private TableColumn<Reponse, String> MessageReponse;
    @FXML
    private TableColumn<Reponse, Button> delete;
    @FXML
    private TableColumn<Reponse, Button> update;
    @FXML
    private TableColumn<Reponse, Button> repondre;
    @FXML
    private Label welcomeLb;
    @FXML
    private TableView<Reclamation> Reclamation;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> reclamation;
    @FXML
    private TableColumn<Reclamation, String> statut;
    @FXML
    private TableColumn<Reclamation, Date> date;
    private User u;
    private Stage stage;
    private Scene scene;
    private Parent root;

    

    public Afficher_reponseController() {
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void getUser(User u){
        this.u=u;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            List<Reponse> reponsionet = RS.recuperer();
            ObservableList<Reponse> olc = FXCollections.observableArrayList(reponsionet);
            
            List<Reclamation> reclamationet = Rs.recuperer();
            ObservableList<Reclamation> olc2 = FXCollections.observableArrayList(reclamationet);
            
            idReponse.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_rep"));
            idReclamation.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_rec"));
            objet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
            MessageReponse.setCellValueFactory(new PropertyValueFactory<Reponse, String>("text_rep"));
            date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_rec"));
            
            statut.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statut"));
            id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_reclamation"));
            reclamation.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("messgae_rec"));
            this.delete();
            this.update();
            this.repondre();
            Reponse.setItems(olc);
            Reclamation.setItems(olc2);
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
                                if (RS.supprimer(Reponse.getItems().get(getIndex())))
                                        {
                                           
                                   
                                    Reponse.getItems().remove(getIndex());
                                    Reponse.refresh();

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
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier_reponse.fxml"));
                           
                            try {
                                  Parent root = loader.load();
                                  welcomeLb.getScene().setRoot(root);
                                  Modifier_reponseController controller = loader.getController();
                                  controller.getUser(u);
                                 System.out.println("hhhhhhh");
                                  Reponse.refresh();
                                 

                                         } catch (IOException ex) {
                                Logger.getLogger(Modifier_reclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Button b = new Button("Repondre");
                        b.setOnAction((event) -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter_reponse.fxml"));
                           
                            try {
                                  Parent root = loader.load();
                                  welcomeLb.getScene().setRoot(root);
                                  Ajouter_reponseController controller = loader.getController();
                                  controller.getUser(u);
                                 System.out.println("hhhhhhh");
                                  Reponse.refresh();
                                 

                                         } catch (IOException ex) {
                                Logger.getLogger(Modifier_reclamationController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void BackHome(ActionEvent event) throws IOException { FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageAdmin.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageAdminController controller = loader.getController();
        controller.setFields(u);
        
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader ();
    loader.setLocation(getClass().getResource("ReclaStat.fxml"));
    loader.load();
    
    Parent parent = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    stage.initStyle(StageStyle.UTILITY);
    stage.show();
    }
    }

