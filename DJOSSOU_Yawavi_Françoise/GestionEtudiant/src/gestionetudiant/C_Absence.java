/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class C_Absence implements Initializable {

    @FXML
    private Text txt_nomprenom;
    @FXML
    private Text text_profil;
    @FXML
    private Button btn_deconnexion;
    @FXML
    private TitledPane menu_classe;
    @FXML
    private TitledPane menu_inscription;
    @FXML
    private Button btn_inscrire;
    @FXML
    private TitledPane menu_professeur;
    @FXML
    private Button btn_affectation;
    @FXML
    private AnchorPane anchor_container;
    @FXML
    private TextField txt_matricule;
    @FXML
    private TextField txt_nom;
    @FXML
    private ComboBox<?> cmb_annee;
    @FXML
    private ComboBox<?> cmb_classe;
    @FXML
    private ComboBox<?> cmb_classe1;
    @FXML
    private TableView<?> tv_matière;
    @FXML
    private TableColumn<?, ?> tc_matiere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleDeconnexion(ActionEvent event) {
    }

    @FXML
    private void handleLoadViewClasse(ActionEvent event) {
    }

    @FXML
    private void handleChangeViewsInsc(ActionEvent event) {
    }

    @FXML
    private void handleChangeviewsAffec(ActionEvent event) {
    }
    
}
