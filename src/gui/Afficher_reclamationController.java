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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.ReclamationService;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Afficher_reclamationController implements Initializable {

    ReclamationService Rs = new ReclamationService();
    ReponseService RS = new ReponseService();
    
    @FXML
    private TableView<Reclamation> Reclamation;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> message;
    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TableColumn<Reclamation, String> statut;
    @FXML
    private TableColumn<Reclamation, Button> delete;
    @FXML
    private TableColumn<Reclamation, Button> update;
    @FXML
    private Label welcomeLb;
    @FXML
    private TextField search;
    @FXML
    private TableView<Reponse> Reponse;
    @FXML
    private TableColumn<Reponse, Integer> idReponse;
    @FXML
    private TableColumn<Reponse, Integer> idReclamation;
    @FXML
    private TableColumn<Reponse, String> MessageReponse;
    private User u;
    private Stage stage;
    private Scene scene;
    private Parent root; 
    
    
    
  
    
    
    
    

    /**
     * Initializes the controller class.
     */
    
    
        
    public void getUser(User u){
        this.u=u;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //int jml = Integer.parseInt(rechercher.getText());
        try {
            
            List<Reponse> reponsionet = RS.recuperer();
            ObservableList<Reponse> olc2 = FXCollections.observableArrayList(reponsionet);
            
            List<Reclamation> reclamationet = Rs.recuperer();
            ObservableList<Reclamation> olc = FXCollections.observableArrayList(reclamationet);
            
            id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_reclamation"));
            objet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
            message.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("messgae_rec"));
            date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_rec"));
            statut.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statut"));
            
            idReponse.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_rep"));
            idReclamation.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id_rec"));
            MessageReponse.setCellValueFactory(new PropertyValueFactory<Reponse, String>("text_rep"));
            
            this.delete();
            this.update();
            //Rs.recherche(43);//id_reclamation
            
            Reclamation.setItems(olc);
            Reponse.setItems(olc2);
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
                                 System.out.println("hhhhhhh");
                                  Reclamation.refresh();
                                 

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
    private void btnSearch(KeyEvent event) {
        //ReclamationService sr=new ReclamationService();
        //list=sr.search(search.getText());
        try {
        List<Reclamation> reclamationet = Rs.recuperer();
            ObservableList<Reclamation> olc = FXCollections.observableArrayList(reclamationet);
            olc = Rs.search(search.getText());
        id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_reclamation"));
            objet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
            message.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("messgae_rec"));
            date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_rec"));
            statut.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statut"));
        Reclamation.setItems(olc);
         System.out.println(olc);
        if(olc.size()!=0){
            try {
                System.out.println(olc);
            } catch (Exception e) {
                Logger.getLogger(Afficher_reclamationController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        }
        catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    @FXML
    private void BackHome(ActionEvent event) throws IOException {
         if(u.getRole().getId_role()==3){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageConducteur.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageConducteurController controller = loader.getController();
        controller.setFields(u);
    }else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageClient.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomePageClientController controller = loader.getController();
        controller.setFields(u);
        }

    
    }

    @FXML
    private void SwitchEmailSend(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mail.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MailController controller = loader.getController();
        controller.getUser(u);
    }
}
