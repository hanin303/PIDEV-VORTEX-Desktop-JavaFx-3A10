/*
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
>>>>>>> 6c8578433897e375fccc5c0bcc77a6741077f8f4
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
=======
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
>>>>>>> 6c8578433897e375fccc5c0bcc77a6741077f8f4
import javafx.stage.Stage;

/**
 *
<<<<<<< HEAD
 * @author MSI
 */
public class FXMain extends Application {
    
    
    

    @Override
    public void start(Stage primaryStage) throws IOException{
//        Parent root=FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        Parent root=FXMLLoader.load(getClass().getResource("DisplayUser.fxml"));
 
        Scene scene = new Scene(root);        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        

           
        };
    

        
         public static void main(String[] args) {
        launch(args);
    }
    }
    

=======
 * @author hanin
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
>>>>>>> 6c8578433897e375fccc5c0bcc77a6741077f8f4
