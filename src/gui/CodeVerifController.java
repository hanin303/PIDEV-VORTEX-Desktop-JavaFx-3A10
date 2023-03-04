/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CodeVerifController implements Initializable {

    @FXML
    private TextField code_text;
    @FXML
    private Button vérifier;
    private String code;
    private String email;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void getCode(String code,String email){
        this.code=code;
        this.email=email;
        
    }
    @FXML
    public void VerifierCode(ActionEvent event) throws IOException{
        System.out.println(code);
        if(code.equals(code_text.getText())){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword2.fxml"));
        Parent root = loader.load();         
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ForgetPassword2Controller controller = loader.getController();
        controller.getEmail(email);
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("Code non valide");
                 alert.showAndWait();
        }
        
    }
    
}
