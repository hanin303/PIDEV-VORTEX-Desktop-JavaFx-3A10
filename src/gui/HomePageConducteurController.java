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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.UserService;
import java.util.Base64;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomePageConducteurController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField cin;
    @FXML
    private TextField path_image;
    @FXML
    private ImageView image;
    UserService us= new UserService();
    private Stage stage;
    private Scene scene;
    private Parent root;    
    private int max=8;
    private User user;
    private String image_path;
    @FXML
    private Button modfier;
    @FXML
    private Button changer;
    private User u;
    @FXML
    private Button disc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setVisible(false);
       path_image.setVisible(false);
       setEmptyImage();

    }   
   public void setEmptyImage(){
       if(image.getImage()==null){
           File file= new File("C:\\PIDEV-VORTEX-Desktop-JavaFx-3A10\\src\\gui\\unknown.png");
           Image i= new Image(file.toURI().toString());
           image.setImage(i);
           image.setFitWidth(image.getFitWidth());
           image.setFitHeight(image.getFitHeight());
       } 
   }
    
    
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

    /**
     * Initializes the controller class.
     */
    
    public void setFields(User u){
    //Base64.Decoder decoder = Base64.getDecoder();
    //decoder.decode(u.getMdp())
    this.u=u;
    id.setText(String.valueOf(u.getId_user()));
    nom.setText(u.getNom());
    prenom.setText(u.getPrenom());
    username.setText(u.getUsername());
    email.setText(u.getEmail());
    String password = new String(u.getMdp());
    mdp.setText(password);    num_tel.setText(String.valueOf(u.getNum_tel()));
    cin.setText(String.valueOf(u.getCin()));
    image_path=u.getImage();
    path_image.setText(u.getImage());
    Image i = new Image(new File(image_path).toURI().toString());
    image.setImage(i);
    image.setFitWidth(image.getFitWidth());
    image.setFitHeight(image.getFitHeight());
    
    }
   @FXML
    private void modifierUser(ActionEvent event) throws NullPointerException{
       //Base64.Encoder encoder = Base64.getEncoder();
       //encoder.encodeToString(mdp.getText().getBytes())
       String email1= us.readByEmail(email.getText()).getEmail();
       String username1 = us.readByUsername(username.getText()).getUsername();
       int cin1= us.readByCin(cin.getText()).getCin();
       if(nom.getText().isEmpty()||prenom.getText().isEmpty()||username.getText().isEmpty()||email.getText().isEmpty()||mdp.getText().isEmpty()||num_tel.getText().isEmpty()||cin.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Vous devez remplir tous les champs"); 
             alert.showAndWait();
       }else{    
       if(EmailValid(email.getText())){
         if(NumberValid(num_tel.getText())){
             if(CinValid(cin.getText())){
                 if(mdp.getText().length()>=8){
                     if(us.readByEmail(email.getText())==null||email.getText().equals(email1)){
                         if(us.readByUsername(username.getText())==null||username.getText().equals(username1)){
                             if(us.readByCin(cin.getText())==null||Integer.parseInt(cin.getText())==cin1){
        List<Object> list= new ArrayList<>(Arrays.asList(nom.getText(),prenom.getText(),username.getText(),email.getText(),mdp.getText(),Integer.parseInt(num_tel.getText()),Integer.parseInt(cin.getText()),path_image.getText()));
        User u2= us.readByID(Integer.parseInt(id.getText()));
        us.update(list,u2.getId_user());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("mise à jour");
		alert.setHeaderText("");
		alert.setContentText("Mise à jour avec succés");
                alert.showAndWait();}
                             else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("numéro de carte d'identité existe déjà");
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
            Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("email existe déjà");
           alert.showAndWait();  }
                 }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("mot de passe invalide");
           alert.setHeaderText(null);
           alert.setContentText("Vous devez entrez une mot de passe valide");
           alert.showAndWait();
                 }

             }else{
                 Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("numéro de carte identité non valide");
                 alert.setHeaderText(null);
                 alert.setContentText("Vous devez entrez un numéro de carte identité valide");
                 alert.showAndWait();
             }
             }else{
                     Alert alert= new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("numéro de téléphone non valide");
                     alert.setHeaderText(null);
                     alert.setContentText("Vous devez entrez un numéro de téléphone valide");
                     alert.showAndWait();
             
                     }
             }else{
               Alert alert= new Alert(Alert.AlertType.ERROR);
               alert.setTitle("e-mail non valide");
               alert.setHeaderText(null);
               alert.setContentText("Vous devez entrez une adresse e-mail valide");
               alert.showAndWait();   
             }
       }
   
}
private void hidePassword(){
    mdp.setVisible(false);
}
@FXML
private void uploadImage(ActionEvent event){
    FileChooser fc= new FileChooser();
    fc.setTitle("choisir une image");
    //choisir les extensions accepté par le programme
    fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image files","*.jpg","*.png","*.jpeg","*.gif"),
            new FileChooser.ExtensionFilter("*.png","*.PNG"),
            new FileChooser.ExtensionFilter("*.jpeg","*.JPEG"),
            new FileChooser.ExtensionFilter("*.gif","*.GIF"),
            new FileChooser.ExtensionFilter("*.jpg","*.JPG")

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
                  Image i= new Image(selectedFile.toURI().toString());
                   image.setImage(i);
                   image.setFitWidth(image.getFitWidth());
                   image.setFitHeight(image.getFitHeight());
                   path_image.setText(path);

    }else{
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setTitle("pas d'image ");
           alert.setHeaderText(null);
           alert.setContentText("vous devez selectionner une image valide ");
           alert.showAndWait();
                   
    }
}

    @FXML
    private void Disconnect(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SwitchReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter_reclamation.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Ajouter_reclamationController controller = loader.getController();
        controller.getUser(u);
    }

    @FXML
    private void SwitchAffiche(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_reclamation.fxml"));
        Parent root = loader.load();          
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Afficher_reclamationController controller = loader.getController();
        controller.getUser(u);
    }
}
