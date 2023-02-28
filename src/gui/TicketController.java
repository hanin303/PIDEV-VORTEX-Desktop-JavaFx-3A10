/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.model.Transfer;
import entity.Reservation;
import entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.stage.Stage;
import service.ReservationService;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author hanin
 */
public class TicketController implements Initializable {

    @FXML
    private ComboBox<String> txtstatus;
    @FXML
    private TextField txtprix;
    @FXML
    private ComboBox<Integer> txtinfo;
    @FXML
    private TableView<Ticket> tvTicket;
    @FXML
    private TableColumn<Ticket, Integer> colid;
    @FXML
    private TableColumn<Ticket, String> colstatus;
    @FXML
    private TableColumn<Ticket, String> colprix;
    @FXML
    private TableColumn<Ticket, Integer> colinfo;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    private Button btnGo;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button buttonpay;

    /**
     * Initializes the controller class.
     */
        public void UpdateTable(){
        List<Ticket> list=new ArrayList<>();
        List<Ticket> list2=new ArrayList<>();
        TicketService ts = new TicketService();
        list=ts.readAll();
            System.out.println(list);
        list2 = list.stream().map(t->new Ticket(t.getId_t(),t.getStatus(),t.getPrix(),t.getId_reservation()))
                .collect(Collectors.toList());
            System.out.println(list2);
        ObservableList<Ticket> obs=FXCollections.observableArrayList(list2);
        colid.setCellValueFactory(new PropertyValueFactory<Ticket ,Integer>("id_t"));
        colstatus.setCellValueFactory(new PropertyValueFactory<Ticket ,String>("status"));
        colprix.setCellValueFactory(new PropertyValueFactory<Ticket ,String>("prix"));
        colinfo.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("id_reservation"));
        tvTicket.setItems(obs);
     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           
        ReservationService rs = new ReservationService();
        ObservableList<Reservation> reservations = FXCollections.observableArrayList(rs.readAll());
        List<Integer> id_reservation = reservations.stream().map(Reservation::getId_reservation).collect(Collectors.toList());
        ObservableList<Integer> observableIds = FXCollections.observableList(id_reservation);
        txtinfo.setItems(observableIds);
        
        ObservableList<String> status = FXCollections.observableArrayList("payer", "non_payer");
        txtstatus.setItems(status);
        txtstatus.setValue("non_payer");    
        UpdateTable();
      
   } 

    @FXML
    private void SupprimerTicket(ActionEvent event) {
    TicketService rs=new TicketService();
    Ticket selected_it = tvTicket.getSelectionModel().getSelectedItem();
    rs.delete(selected_it.getId_t());
       Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Voulez-vous vraiment supprimer ce ticket ?");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void ModifierTicket(ActionEvent event) {
    List<Object> list = new ArrayList<>(Arrays.asList(txtstatus.getValue(), txtprix.getText(), txtinfo.getValue()));
    TicketService rs=new TicketService();
    Ticket selected_it = tvTicket.getSelectionModel().getSelectedItem();
    rs.update(list,selected_it.getId_t());
     Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
    UpdateTable();
   
        
    }

    @FXML
    private void AjouterTicket(ActionEvent event) {
    String st = txtstatus.getValue();
    String prix=txtprix.getText();
    if(st.isEmpty()||prix.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else{
        
    ReservationService rs=new ReservationService();
    Ticket res = new Ticket(txtstatus.getValue(), txtprix.getText(),rs.readByID(txtinfo.getValue())); 
    System.out.println(res);
    TicketService ts = new TicketService();
    ts.insert(res);
    
//       Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("");
//		alert.setHeaderText("");
//		alert.setContentText("Ticket ajouter avec succés");
//                alert.showAndWait();
    UpdateTable();
    }
    }

    @FXML
    private void Switchscreenreservation(ActionEvent event)throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void controlesaisieprix(KeyEvent event) {
        
       if (event.getCharacter().matches("[^\\e\t\r\\d+$]")){
           event.consume();
           txtprix.setStyle("-fx-border-color:red");
           event.consume();
       }else
           
           txtprix.setStyle("-fx-border-color:green");
       }
    
   
    
    @FXML
    private void PayerTicket(ActionEvent event) throws StripeException {
            Stripe.apiKey = "sk_test_51Mg75HLu7B1VCpQ0mSqMJ2ucVpFhYufToAINJK1T6932bHEpdiBaD6tMbEDEwA5Aa4Fh1b9WBLhXcb02dhZNLM79008NYLc3Cf";
                 //id=ps.insertretrieveid(p1);
                 //System.out.println(id);
                 
                try {
             //Create a customer object for the user who is paying
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("email", "haninbenjemaa@gmail.com");
            Customer payer = Customer.create(customerParams);
            System.out.println(payer.getId()); 
            
//            Customer a = Customer.retrieve("cus_NRSVBhMI96zG1n");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(a)); 
             
            Map<String, Object> retrieveParams = new HashMap<String, Object>();
		List<String> expandList = new ArrayList<String>();
		expandList.add("sources");
		retrieveParams.put("expand", expandList);
                //add customer id here : it will start with cus_
		Customer customer = Customer.retrieve(payer.getId(), retrieveParams, null); 
                
                //add card details //add this card parameters to token parameters  
		Map<String, Object> cardParam = new HashMap<String, Object>(); 
		cardParam.put("number", "4242424242424242");
		cardParam.put("exp_month", "11");
		cardParam.put("exp_year", "2026");
		cardParam.put("cvc", "123");

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam); // create a token

		Map<String, Object> source = new HashMap<String, Object>();
                
		source.put("source", token.getId()); //add token as source
                
                // add the customer details to which card is need to link
		Card card = (Card)customer.getSources().create(source); 
		String cardDetails = card.toJson();
		System.out.println("Card Details : " + cardDetails);
		customer = Customer.retrieve(payer.getId());//change the customer id or use to get customer by id.
		System.out.println("After adding card, customer details : " + customer);
                      
               //PaymentMethod paymentMethod = PaymentMethod.create(cardParam);
               
               System.out.println(customer.getId());       
               //Use the payment method to make a charge
               Map<String, Object> chargeParams = new HashMap<String, Object>();
               chargeParams.put("amount", "500");
               chargeParams.put("currency", "usd");
               //chargeParams.put("description", "Example charge");
               //chargeParams.put("source", token.getId());
               chargeParams.put("customer", customer.getId());
               Charge charge = Charge.create(chargeParams);      
       
            System.out.println("Payment successful!");
        } catch (StripeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    
   
    
    }
    
    
    
    
    }

    
