   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Ligne;
import entity.MoyTran;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import connexionbd.utils.DataSource;
import javafx.scene.control.Label;

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
    @FXML
    private TextField txtch;
    @FXML
    private TextField searchField;
    @FXML
    private Label test;
    @FXML
    private TextField num;
    @FXML
    private TableColumn<MoyTran, Integer> mat1;
    MoyTranService ms=new MoyTranService();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    public void UpdateTable(){
        List<MoyTran> list=new ArrayList<>();
        
        MoyTranService ts=new MoyTranService();
        if (txtch.getText().length() == 0){
        list=ts.readAll();
        }else{
            list.add(ts.readByID(Integer.parseInt(txtch.getText())));
        }
        ObservableList<MoyTran> obs=FXCollections.observableArrayList(list);
        idmoy.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("id_moy"));
        mat.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("matricule"));
        mat1.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("num"));
        cap.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("capacite"));
        tv.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("type_vehicule"));
        marque.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("marque"));
        etat.setCellValueFactory(new PropertyValueFactory<MoyTran ,String>("etat"));
        li.setCellValueFactory(new PropertyValueFactory<MoyTran ,Integer>("id_ligne"));
        tabmoy.setItems(obs);
    }
       

    public CRUDMOYController() {
       conn = DataSource.getInstance().getCnx();
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
        String Dep=txtcap.getText();
    String ver=typev.getValue();
    String type=txtmar.getText();
    String typee=txte.getValue();
   // Integer typeee=txtl.getValue();
    
    if(Dep.isEmpty()||ver.isEmpty()||type.isEmpty()||typee.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else{
        if(ms.readMatricule(Integer.parseInt(txtm.getText()))==null){
            
        
        MoyTran t = new MoyTran(Integer.parseInt(txtm.getText()),Integer.parseInt(num.getText()),Integer.parseInt(txtcap.getText()),typev.getValue(),txtmar.getText(),txte.getValue(),txtl.getValue());
        MoyTranService s =new MoyTranService();
        s.insert(t);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Insertion avec succés");
                alert.showAndWait();
        UpdateTable();
        }else{
             Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Matricule existe déjà");
                alert.showAndWait();
        }
    }
        
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

    private void update(ActionEvent event) {
        List<Object> list = new ArrayList<>(Arrays.asList(Integer.parseInt(txtm.getText()),Integer.parseInt(num.getText()),Integer.parseInt(txtcap.getText()), 
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
    private void limit4(KeyEvent event) {
       if(!event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        txtm.setStyle("-fx-border-color: red");
    }else{
        txtm.setStyle("-fx-border-color: green");
    }
    }

    private MoyTran search(ActionEvent event) {
         MoyTranService is=new MoyTranService();
         int t = Integer.parseInt(txtch.getText());
         
         Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Insertion avec succés");
                alert.showAndWait();
                return is.readByID(t);
        

        
    }

    @FXML
    private void genPdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
        
      
        
    String sql = "SELECT* from moyentransport";
    
      PreparedStatement ste=conn.prepareStatement(sql);
   //  Statement ste=conn.createStatement();
     ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListesdesmoyenTran1.pdf"));

    doc.open();
    Paragraph title = new Paragraph("La liste des moyens de transport" , FontFactory.getFont("COURIER", 14)); 
      title.setAlignment(Element.ALIGN_CENTER);
      doc.add(title);
   
    //doc.add(new Paragraph("   "));
    //doc.add(new Paragraph("                                  Liste Des moyens de transport                                  "));
    
   // doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(8);
    table.setWidthPercentage(115);
    table.setSpacingBefore(20);
    table.setSpacingAfter(20);
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("ID Moy", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("Matricule", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
     cell = new PdfPCell(new Phrase("Numéro", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Capacité", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    
  
    cell = new PdfPCell(new Phrase("type Véhicule", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    
     cell = new PdfPCell(new Phrase("Marque", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("Etat", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase("ligne", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    

    while (rs.next()) {

       MoyTran m = new MoyTran();
        m.setId_moy(rs.getInt("id_moy"));
        m.setMatricule(rs.getInt("matricule"));
        m.setNum(rs.getInt("num"));
        m.setCapacite(rs.getInt("capacite"));
        m.setType_vehicule(rs.getString("type_vehicule"));
        m.setMarque(rs.getString("marque"));
        m.setEtat(rs.getString("etat"));
        m.setId_ligne(rs.getInt("id_ligne"));
        System.out.println(m);
       
        
        cell = new PdfPCell(new Phrase(String.valueOf(m.getId_moy()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.PINK);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(m.getMatricule()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(m.getNum()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(String.valueOf(m.getCapacite()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
          cell = new PdfPCell(new Phrase(m.getType_vehicule(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
         cell = new PdfPCell(new Phrase(m.getMarque(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
          cell = new PdfPCell(new Phrase(m.getEtat(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(m.getId_ligne()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListesdesmoyenTran1.pdf"));
        
        
    }

    @FXML
    private void searchField(ActionEvent event) {
        ObservableList<MoyTran> transportList = FXCollections.observableArrayList();
         transportList.clear();
       MoyTranService m = new MoyTranService();
        transportList.setAll(m.readAll().stream().filter((art)
                -> art.getMarque().toLowerCase().contains(searchField.getText().toLowerCase())
                || art.getEtat().toLowerCase().contains(searchField.getText().toLowerCase())
               
               
        //                || Integer.toString(art.getPrixAchat()).equals(searchTF.getText())
        //                || Integer.toString(art.getPrixVente()).equals(searchTF.getText())

        ).collect(Collectors.toList()));
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Total des Moyens de transport de cette marque : "+ transportList.size());
                alert.showAndWait();
        //test.setText("Total des Moyens de transport de cette marque : " + transportList.size());
    }

    @FXML
    private void stat(ActionEvent event) {
        
         
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BarChartm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    @FXML
    private void switchmmp(ActionEvent event) throws IOException {
           root1 = FXMLLoader.load(getClass().getResource("CRUDLIGNE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchtmp(MouseEvent event) throws IOException {
           root1 = FXMLLoader.load(getClass().getResource("CRUDLIGNE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modif(ActionEvent event) {
         String Dep=txtcap.getText();
    String ver=typev.getValue();
    String type=txtmar.getText();
    String typee=txte.getValue();
   // Integer typeee=txtl.getValue();
    
    if(Dep.isEmpty()||ver.isEmpty()||type.isEmpty()||typee.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
    }else {
        if(ms.readMatricule(Integer.parseInt(txtm.getText()))==null){ 
        List<Object> list = new ArrayList<>(Arrays.asList(Integer.parseInt(txtm.getText()),Integer.parseInt(num.getText()),Integer.parseInt(txtcap.getText()), typev.getValue(),txtmar.getText(),txte.getValue(),txtl.getValue()));
//       MoyTran m = new MoyTran(Integer.parseInt(txtm.getText()),Integer.parseInt(num.getText()),Integer.parseInt(txtcap.getText()), typev.getValue(),txtmar.getText(),txte.getValue(),txtl.getValue());
        MoyTranService rs = new MoyTranService();
    MoyTran selected_it = tabmoy.getSelectionModel().getSelectedItem();
        System.out.println(selected_it);
    rs.update(list,selected_it.getId_moy());
    UpdateTable();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();
                UpdateTable();
       }else{
             Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Matricule existe déjà");
                alert.showAndWait();
                }
        
    }

    
    }
   

   
    
    
}

   
    

