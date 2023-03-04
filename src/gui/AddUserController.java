/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.RoleService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField cin;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField path_image;
    @FXML 
    private PasswordField mdp;
    @FXML
    private Button inscri;
    @FXML
    private Button upload;
    
    private UserService us;
    private RoleService rs;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
        
    int max=8;
    @FXML
    private PasswordField mdp1;
   
    
    
 

    private boolean EmailValid(String email){
        if(email==null||email.isEmpty()){
            return false;
        }
        Pattern pattern= Pattern.compile("^[\\w\\d._%+-]+@[\\w\\d.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public boolean NumberValid(String num_tel) {
    // Expression régulière pour un numéro de téléphone de 8 chiffres (sans les indicateurs de pays)
    String regex = "^[0-9]{8}$";
    return num_tel.matches(regex);
}
    
    public boolean CinValid(String cin) {
    // Expression régulière pour un numéro de téléphone de 8 chiffres (sans les indicateurs de pays)
    String regex = "^[0-9]{8}$";
    return cin.matches(regex);
}
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rs= new RoleService();
        us= new UserService();
        path_image.setVisible(false);



    }
    
    @FXML
    public void ajouterUser(ActionEvent event) throws IOException{
       Base64.Encoder encoder = Base64.getEncoder();
       if(nom.getText().isEmpty()||prenom.getText().isEmpty()||username.getText().isEmpty()||email.getText().isEmpty()||mdp.getText().isEmpty()||num_tel.getText().isEmpty()||cin.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
       }else{
       if(EmailValid(email.getText())){
         if(NumberValid(num_tel.getText())){
             if(CinValid(cin.getText())){
                 if(mdp.getText().length()>=8){
                     if(mdp1.getText().equals(mdp.getText())){
                         if(us.readByEmail(email.getText())==null){
                             if(us.readByUsername(username.getText())==null){
                   User u = new User(nom.getText(),prenom.getText(),username.getText(),email.getText(),encoder.encodeToString(mdp.getText().getBytes()),Integer.parseInt(num_tel.getText()),Integer.parseInt(cin.getText()),path_image.getText(),rs.readByID(4));
                   us.insert(u);
                 Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("création");
		alert.setHeaderText("");
		alert.setContentText("compte crée avec succés");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageClient.fxml"));
                Parent root = loader.load();                      
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                HomePageClientController controller = loader.getController();
                controller.setFields(u);
                             }else{
                 Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("email existe déjà");
                 alert.showAndWait();
                             }
                         }else{
                 Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("username existe déjà");
                 alert.showAndWait();
                         }
                
}else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("mots de passe non identiques");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez saisir des mots de passe identiques");
           alert.showAndWait();        
                     }
                 }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("mot de passe invalide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une mot de passe valide");
           alert.showAndWait();
                     
                 }
             }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("numéro de carte identité non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez un numéro de carte identité valide");
           alert.showAndWait();
             }
                  }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("numéro de téléphone non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez un numéro de téléphone valide");
           alert.showAndWait();
         }
            }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("e-mail non valide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une adresse e-mail valide");
           alert.showAndWait();
            }
    }
    }
    
private void clearFields(){
    nom.setText("");
    prenom.setText("");
    username.setText("");
    email.setText("");
    mdp.setText("");
    num_tel.setText("");
    cin.setText("");
    
}   

@FXML
private void hidePassword(){
    mdp.setVisible(false);
    mdp1.setVisible(false);
}

@FXML
private void uploadImage(ActionEvent event){
     FileChooser fc= new FileChooser();
    fc.setTitle("choisir une image");
    //choisir les extensions accepté par le programme
    fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image files","*.png","*.jpeg","*.gif"),
            new FileChooser.ExtensionFilter("*.png","*.PNG"),
            new FileChooser.ExtensionFilter("*.jpeg","*.JPEG"),
            new FileChooser.ExtensionFilter("*.gif","*.GIF")
            );
          
           File selectedFile = fc.showOpenDialog(null);
           Random rand = new Random();

    if (selectedFile != null) {
               
                   // schema d'image
                   Path fromPath = selectedFile.toPath();
                   //schema de destination
                   String path = "C:\\xampp\\htdocs\\images\\"+rand.nextInt(99999999)+selectedFile.getName();
                   Path toPath = Paths.get(path);
               try {
                   // Copy the selected file to your project directory
                 
                   Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);

               } catch (IOException ex) {
                   Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
               }
                  Image image= new Image(selectedFile.toURI().toString());
                   imageview.setImage(image);
                   imageview.setFitWidth(imageview.getFitWidth());
                   imageview.setFitHeight(imageview.getFitHeight());
                   
                   path_image.setText(path);

    }else{
           Alert alert= new Alert(AlertType.ERROR);
           alert.setTitle("pas d'image ");
           alert.setHeaderText(null);
           alert.setContentText("vous devez selectionner une image valide ");
           alert.showAndWait();
         }
               
               
               
       }     

    @FXML
    private void SwitchLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

 