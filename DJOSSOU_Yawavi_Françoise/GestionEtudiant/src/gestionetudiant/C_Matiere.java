/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import Models.Matiere;
import Models.User;
import Service.GestionEtudiantImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class C_Matiere implements Initializable {
    private GestionEtudiantImpl service =new GestionEtudiantImpl();
    private static User userconnect=C_connexionController.getUser();
    private ObservableList<Matiere> obvMatiere;

    @FXML
    private TableColumn<?, ?> tblc_id;
    @FXML
    private TableColumn<?, ?> tblc_Libelle;
    @FXML
    private TextField txt_Libelle;
    @FXML
    private TableView<Matiere> tblv_Matiere;
    @FXML
    private Pane pane_addMatiere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Matiere> donnee = FXCollections.observableArrayList();
        // TODO            
        tblc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblc_Libelle.setCellValueFactory(new PropertyValueFactory<>("name"));
        donnee.addAll(service.listerMatiere());
        tblv_Matiere.setItems(donnee);
        tblv_Matiere.refresh();
    }    


    @FXML
    private void handle_InsertMatiere(ActionEvent event) {
        ObservableList<Matiere> donnee = FXCollections.observableArrayList();
        String libelle = txt_Libelle.getText(); 
        Matiere mat = new Matiere(libelle);
        service.addMatiere(mat);
        donnee.addAll(service.listerMatiere());
        tblv_Matiere.setItems(donnee);
        tblv_Matiere.refresh();  
    }

    
}
