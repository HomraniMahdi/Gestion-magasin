/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Magazin;
import Model.Produit;
import Services.ServiceMagazin;
import java.io.IOException;
import java.net.URL;
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
public class GestionMagasinController implements Initializable {

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
    private TableView<Magazin> TableMagasin;
    private TableColumn<Magazin, Integer> IdPoduitTab;
    @FXML
    private TextField adresseft;
    @FXML
    private TextField designtf;
    @FXML
    private TableColumn<Magazin, Integer> IdMagasinTab;
    @FXML
    private TableColumn<Magazin, Integer> nomTab;
    @FXML
    private TableColumn<Magazin, String> adresseTab;
    @FXML
    private TableColumn<Magazin, String> desigTab;
    
    ServiceMagazin sm =new ServiceMagazin();
    int id;
    Magazin p;
    ObservableList<Magazin> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        recherche_avance();
    }    

    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(sm.afficherMagazin());
 
        IdMagasinTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseTab.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        desigTab.setCellValueFactory(new PropertyValueFactory<>("desig"));
        TableMagasin.setItems(data);
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
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/AjouterMagasin.fxml"));
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
        Magazin m=TableMagasin.getSelectionModel().getSelectedItem();
        id = m.getId();
        nomft.setText(m.getNom());
        adresseft.setText(m.getAdresse());
        designtf.setText(m.getDesig());
    }

    @FXML
    private void ModifierProduit(ActionEvent event) {
        Magazin p =new Magazin(
                nomft.getText(),
                adresseft.getText(),
                designtf.getText()

        );
            sm.update(id,p);
       refreshlist();
       recherche_avance();
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
            int Id;
        Id=TableMagasin.getSelectionModel().getSelectedItem().getId();
        sm.delete(id,p);
        refreshlist();
        recherche_avance();
    }

            private void recherche_avance() {
        FilteredList<Magazin> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(pv -> {
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (newValue == null || newValue.isEmpty()) {
			return true;
                    }
                    if (String.valueOf(pv.getId()).indexOf(lowerCaseFilter)!=-1) {
                        return true; 
                    } 
                    
                    if (pv.getAdresse().indexOf(lowerCaseFilter)!=-1) {
                        return true; 
                    } 
                    if (pv.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true; // Filter matches first name.
                    }
                    if (pv.getDesig().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true; // Filter matches first name.
                    }
                    else
			return false; // Does not match.
                    });
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Magazin> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TableMagasin.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TableMagasin.setItems(sortedData);
         
        
    }
    @FXML
    private void Statistique(ActionEvent event) {
    }
    
}
