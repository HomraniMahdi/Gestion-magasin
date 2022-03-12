/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Produit;
import Services.ServiceProduit;
import Util.JfreeChartApi;
import com.twilio.rest.chat.v1.service.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class GestionProduitController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private TextField TFSearch;
    @FXML
    private Label label;
    @FXML
    private TextField nomft;
    @FXML
    private TextField prixft;
    @FXML
    private ImageView image_view;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Produit> TableProduit;
    @FXML
    private TableColumn<Produit, Integer> IdPoduitTab;
    @FXML
    private TableColumn<Produit, String> nomTab;
    @FXML
    private TableColumn<Produit, Float> prixTab;
    @FXML
    private TableColumn<Produit, LocalDateTime> dateTab;

    /**
     * Initializes the controller class.
     */
    ServiceProduit sp =new ServiceProduit();
    int id;
    Produit p;
    ObservableList<Produit> data=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        recherche_avance();
    }    
       public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(sp.afficherProduit());
 
        IdPoduitTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixTab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateTab.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableProduit.setItems(data);
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

    @FXML
    private void AjouterProduit(ActionEvent event) {
                                try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/AjouterProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Produit p=TableProduit.getSelectionModel().getSelectedItem();
        id=p.getId();
        nomft.setText(p.getNom());
        prixft.setText(Float.toString(p.getPrix()));
        date.setValue(p.getDate().toLocalDate());
    }

    @FXML
    private void ModifierProduit(ActionEvent event) {
               Produit p =new Produit(
                nomft.getText(),
                Float.parseFloat(prixft.getText()),
                date.getValue().atStartOfDay()
        );
            sp.update(id,p);
       refreshlist();
       recherche_avance();
    }

        private void recherche_avance() {
        FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(pv -> {
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (newValue == null || newValue.isEmpty()) {
			return true;
                    }
                    if (String.valueOf(pv.getId()).indexOf(lowerCaseFilter)!=-1) {
                        return true; 
                    } 
                    
                    if (String.valueOf(pv.getPrix()).indexOf(lowerCaseFilter)!=-1) {
                        return true; 
                    } 
                    if (pv.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true; // Filter matches first name.
                    }
                    else
			return false; // Does not match.
                    });
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TableProduit.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TableProduit.setItems(sortedData);
         
        
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
                int Id;
        Id=TableProduit.getSelectionModel().getSelectedItem().getId();
        sp.delete(id,p);
        refreshlist();
        recherche_avance();
    }

    @FXML
    private void Statistique(ActionEvent event) {
                ServiceProduit ms = new ServiceProduit();
        HashMap<String, Double> data = ms.StatistiqueParNom();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
    
}
