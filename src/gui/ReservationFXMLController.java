/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connexionbd.utils.DataSource;
import entity.Iteneraire;
import entity.Reservation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ItineraireService;
import service.ReservationService;
import connexionbd.utils.DataSource;
import javafx.scene.control.Cell;
import javafx.scene.layout.Border;
/**
 * FXML Controller class
 *
 * @author hanin
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private ComboBox<Integer> txtitineraire;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtheuredepart;
    @FXML
    private TextField txtheurearrive;
    @FXML
    private TextField txttype;
    @FXML
    private ComboBox<Integer> txttransport;
    @FXML
    private ComboBox<String> txtstatus;
    @FXML
    private ComboBox<Integer> txtusr;
    @FXML
    private TableView<Reservation> tvReservation;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, LocalDate> colDate;
    @FXML
    private TableColumn<Reservation, String> colHdepart;
    @FXML
    private TableColumn<Reservation, String> colHarrive;
    @FXML
    private TableColumn<Reservation, Integer> colit;
    @FXML
    private TableColumn<Reservation, Integer> colTransport;
    @FXML
    private TableColumn<Reservation, String> colType;
    @FXML
    private TableColumn<Reservation, String> colStatus;
    @FXML
    private TableColumn<Reservation, Integer> colusr;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnGo;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField idsearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button idPrint;
    
    private Connection conn;

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    public void UpdateTable(){
        List<Reservation> list=new ArrayList<>();
        List<Reservation> list2=new ArrayList<>();
        ReservationService rs = new ReservationService();
        if(idsearch.getText().length() == 0)
        list=rs.readAll();
        else 
        list.add(rs.readByID(Integer.parseInt(idsearch.getText())));
        
        System.out.println(list);
        list2 = list.stream().map(re->new Reservation(re.getId_reservation(),re.getStatus(),re.getDate_reservation(),re.getHeure_depart(),
        re.getHeure_arrive(),re.getId_user(),re.getId_moy(),re.getId_it(),re.getType_ticket())).collect(Collectors.toList());
        ObservableList<Reservation> obs=FXCollections.observableArrayList(list2);
        colid.setCellValueFactory(new PropertyValueFactory<Reservation ,Integer>("id_reservation"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reservation ,LocalDate>("date_reservation"));
        colHdepart.setCellValueFactory(new PropertyValueFactory<Reservation ,String>("heure_depart"));
        colHarrive.setCellValueFactory(new PropertyValueFactory<Reservation ,String>("heure_arrive"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation,String>("status"));
        colusr.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_user"));
        colTransport.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_moy"));
        colit.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_it"));
        colType.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type_ticket"));
        tvReservation.setItems(obs);
     
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        ItineraireService is = new ItineraireService();
        ObservableList<Iteneraire> itineraires = FXCollections.observableArrayList(is.readAll());
         List<Integer> id_it_list = itineraires.stream().map(Iteneraire::getId).collect(Collectors.toList());
        ObservableList<Integer> observableIds_IT = FXCollections.observableList(id_it_list);
        txtitineraire.setItems(observableIds_IT);
      
         ReservationService rss = new ReservationService();
         ObservableList<Reservation> resList = FXCollections.observableArrayList(rss.readAll());
         List<Integer> id_moy_trans = resList.stream().map(Reservation::getId_moy).collect(Collectors.toList());
         ObservableList<Integer> observableIds_moy = FXCollections.observableList(id_moy_trans);
         txttransport.setItems(observableIds_moy);
         
         
         List<Integer> id_user = resList.stream().map(Reservation::getId_user).collect(Collectors.toList());
         ObservableList<Integer> observableIds_user = FXCollections.observableList(id_user);
         txtusr.setItems(observableIds_user);
        
        
        ObservableList<String> status = FXCollections.observableArrayList("confirme", "en_attente","annule");
        txtstatus.setItems(status);
        txtstatus.setValue("en_attente");
        
         UpdateTable();
    }

    public ReservationFXMLController() {
         conn=DataSource.getInstance().getCnx();
    }
        

   @FXML
private void AjouterReservation(ActionEvent event) {
    String heureDep=txtheuredepart.getText();
    String heureArriver=txtheurearrive.getText();
    String type=txttype.getText();
    if(heureDep.isEmpty()||heureArriver.isEmpty()||type.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else{
    ItineraireService ts=new ItineraireService();
    Reservation res = new Reservation(txtstatus.getValue(),txtdate.getValue(), txtheuredepart.getText(), txtheurearrive.getText(), txtusr.getValue(),txttransport.getValue(),ts.readByID(txtitineraire.getValue()),txttype.getText()); 
    System.out.println(res);
    ReservationService rs = new ReservationService();
    rs.insert(res);
  
//     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setTitle("");
//		alert.setHeaderText("");
//		alert.setContentText("Reservation ajouter avec succés");
//                alert.showAndWait();
    UpdateTable();
}
}

    @FXML
    private void ModifierReservation(ActionEvent event) {
    //List<Object> list = new ArrayList<>(Arrays.asList(txtstatus.getValue(),txtheuredepart.getText(),txtheurearrive.getText(),txtusr.getValue(),txttransport.getValue(),txtitineraire.getValue(),txttype.getText()));
    Reservation r = new Reservation((String)txtstatus.getValue(),txtdate.getValue(),txtheuredepart.getText(),txtheurearrive.getText(),txtusr.getValue(),txttransport.getValue(),txtitineraire.getValue(),txttype.getText());
        //System.out.println(r);
    ReservationService rs=new ReservationService();
    Reservation selected_it = tvReservation.getSelectionModel().getSelectedItem();
        System.out.println(selected_it);
    rs.update2(r,selected_it.getId_reservation());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) {
    ReservationService rs=new ReservationService();
    Reservation selected_it = tvReservation.getSelectionModel().getSelectedItem();
    rs.delete(selected_it.getId_reservation());
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Voulez-vous vraiment supprimer cette Réservation ? ");
                alert.showAndWait();
    UpdateTable();
    }

    @FXML
    private void switchscreenticket(ActionEvent event)throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void ControleSaisieDate(KeyEvent event) {
        
        txtdate.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == null || newValue.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La date sélectionnée est invalide !!");
            alert.showAndWait();
            txtdate.setValue(oldValue);
        }
         });
        
    }
    
//   private void displayById(ActionEvent event) {
// 
//        if(idsearch.getText().isEmpty()){
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//		alert.setTitle("champ vide ");
//		alert.setHeaderText("");
//		alert.setContentText("vous devez remplir le champ");
//                alert.showAndWait();
//                UpdateTable();
//        }else{
//             int id_search= Integer.parseInt(idsearch.getText());
//        ReservationService rs = new ReservationService();
//        Reservation r= rs.readByID(id_search);
//       
//        if(r != null){
//            tvReservation.getItems().clear();
//            colid.setText(String.valueOf(r.getId_reservation()));
//            colDate.setText(String.valueOf(r.getDate_reservation()));
//            colHdepart.setText(r.getHeure_depart());
//            colHarrive.setText(r.getHeure_arrive());
//            colit.setText(String.valueOf(r.getId_it()));
//            colTransport.setText(String.valueOf(r.getId_moy()));
//            colType.setText(r.getType_ticket());
//            colStatus.setText(r.getStatus());
//            colusr.setText(String.valueOf(r.getId_user()));
//        }else{
//            Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("");
//		alert.setHeaderText("");
//		alert.setContentText("pas de utilisateur avec cet id");
//                alert.showAndWait();
//                 UpdateTable();
//        }
//    
//        }
//       
//    }
    
    
    @FXML
    private void ControleSaisieHeure(KeyEvent event) {
        txtheuredepart.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.trim().isEmpty()) {
            txtheuredepart.setStyle("-fx-border-color: red ;");
            txtheuredepart.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le saisie de l'heure depart est obligatoire !!");
            alert.showAndWait();
        } else {
            txtheuredepart.setStyle("-fx-border-color: transparent ;");
        }
        });
                }
    

   
    @FXML
    private void addpdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException{
        String sql = "SELECT status,heure_depart,heure_arrive,id_client,id_moy,id_it,type_ticket from reservation";
    
     Statement st=conn.createStatement();
     ResultSet rs = st.executeQuery(sql);

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesReservations.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste De mes Reservations *********************************** "));
    doc.add(new Paragraph("   "));
    

    PdfPTable table = new PdfPTable(7);
    table.setWidthPercentage(100);
    PdfPCell cell;
    
  
//    cell = new PdfPCell(new Phrase("Date", FontFactory.getFont("Comic Sans MS", 14)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//    
//    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Heure Depart", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("Heure Arrive", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("id_it", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("Type ticket", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("id_moy", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("status", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("id_user", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    while (rs.next()) {

        Reservation r = new Reservation();
      
//        r.setDate_reservation((java.sql.Date.valueOf(rs.getString("date_reservation"))));
        r.setHeure_depart(rs.getString("heure_depart"));
        r.setHeure_arrive(rs.getString("heure_arrive"));
        r.setId_moy(rs.getInt("id_moy"));
        r.setId_it(rs.getInt("id_it"));
        r.setType_ticket(rs.getString("type_ticket"));
        r.setStatus(rs.getString("status"));
        r.setId_user(rs.getInt("id_client"));
        
//        cell = new PdfPCell(new Phrase(String.valueOf(r.getDate_reservation()), FontFactory.getFont("Comic Sans MS", 12)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);

      
        cell = new PdfPCell(new Phrase(r.getHeure_depart(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(r.getHeure_arrive(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(r.getId_it()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(r.getType_ticket(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
         cell = new PdfPCell(new Phrase(String.valueOf(r.getId_moy()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(r.getStatus(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(r.getId_user()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesReservations.pdf"));
    }
    
    
    
    
   
}


