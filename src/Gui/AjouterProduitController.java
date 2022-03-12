/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Produit;
import Services.ServiceProduit;
import Util.JavaMailUtils;
import Util.MailerApi;
import Util.SMSApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField nomft;
    @FXML
    private TextField prixft;
    @FXML
    private DatePicker date;
    @FXML
    private TextField Emailtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Acceuil(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertProduit(){
        ServiceProduit Sp = new ServiceProduit();
        Produit p = new Produit(nomft.getText(),
                                Integer.parseInt(prixft.getText()),
                                date.getValue().atStartOfDay());  
        
                        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Produit créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail(Emailtf.getText(), "Admin.");
        
                //SEND SMS
        SMSApi sms = new SMSApi();
        //sms.sendSMS("+216"+numero.getText(), "Admin.");
       // sms.sendSMS("+21620071775", "Admin.");
        
        Sp.add(p);
        
    }

    @FXML
    private void Enregister(ActionEvent event) {
        InsertProduit();
    }
    
}
