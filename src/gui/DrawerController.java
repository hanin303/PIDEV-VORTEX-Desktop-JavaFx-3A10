/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hanin
 */
public class DrawerController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger myHumburger;
    @FXML
    private JFXDrawer drawer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
             VBox box = FXMLLoader.load(getClass().getResource("SlidePane.fxml"));
             
             drawer.setSidePane(box);
            
            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(myHumburger);
            burgerTask2.setRate(-1);
            myHumburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate()* -1);
                burgerTask2.play();
                
                if (drawer.isOpened())
                    drawer.close();
                else
                    drawer.open();
                
            });   
        } catch (IOException ex) {
            Logger.getLogger(DrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }  
    public void setController(AnchorPane anchorPane){
        
    }
    
}
