package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author kalee
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
        Parent root1 =FXMLLoader.load(getClass().getResource("Trajet.fxml"));
        Scene scene1 = new Scene(root1);
        

        primaryStage.setScene(scene1);
        primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
    public static void main(String[] args) {
        launch(args);
    }

    }

    
        
         

/**
 * @param args the command line arguments
 */
