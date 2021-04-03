/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import Models.Classe;
import Models.Etudiant;
import Models.Inscription;
import Models.User;
import Service.GestionEtudiantImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Hilary DJENGUE
 */
public class C_inscrire implements Initializable {
    private String nameController=null;
    FunctionController function= new FunctionController();
    private GestionEtudiantImpl service =new GestionEtudiantImpl();
    private static User userconnect=C_connexionController.getUser();
    private ObservableList<Inscription> obvMatiere;

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
    private ComboBox<String> cmb_classe;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_tuteur;
    @FXML
    private Button btn_inscription;
    @FXML
    private TableColumn<?, ?> tblc_id;
    @FXML
    private TableColumn<?, ?> tblc_nomC;
    @FXML
    private TableColumn<?, ?> tblc_classe;
    @FXML
    private TableColumn<?, ?> tblc_annee;
    @FXML
    private TableView<Inscription> tblv_incription;
    @FXML
    private TextField txt_annee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(cmb_classe.getUserData());
                for (Classe classe : service.listerClasse()) {     
                cmb_classe.getItems().add(classe.getLibelle()); 
        }
        ObservableList<Inscription> donnee = FXCollections.observableArrayList();
        // TODO            
        tblc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblc_nomC.setCellValueFactory(new PropertyValueFactory<>("etu"));   
        tblc_classe.setCellValueFactory(new PropertyValueFactory<>("classe"));  
        tblc_annee.setCellValueFactory(new PropertyValueFactory<>("année"));
        donnee.addAll(service.listerInscription());
        tblv_incription.setItems(donnee);
        tblv_incription.refresh();
    }    

    @FXML
    private void handleDeconnexion(ActionEvent event) {
    }

    @FXML
    private void handleLoadViewClasse(ActionEvent event) {
    }

    @FXML
    private void handleChangeviewsAffec(ActionEvent event) throws IOException {
         nameController="affectation";         
         function.changeViews(btn_affectation, nameController);
      
    }
    @FXML
    private void handleChangeViewsInsc(ActionEvent event) throws IOException {
         nameController="inscrire";         
         function.changeViews(btn_affectation, nameController);
       
    }

    @FXML
    private void handleInscription(ActionEvent event) {
        /*        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        if(txt_nom  == null || txt_prenom  == null || txt_tuteur  == null || txt_annee == null ){
        alert.setContentText("Veuillez entrez des valeurs");
        alert.showAndWait();
        }else{
        ObservableList<Inscription> donnee = FXCollections.observableArrayList();
        String annee = txt_annee.getText();
        String tuteur = txt_tuteur.getText();
        String nomComplet= txt_nom.getText()+txt_prenom.getText();
        Classe cl=new Classe();
        cl=service.rechercheClasseLibelle(cmb_classe.getValue());
        Etudiant etu = new Etudiant();
        etu.setTuteur(tuteur);
        etu.setNomComplet(nomComplet);
        service.inscrireEtudiant(etu, cl, annee);
        alert.setContentText("Info Enregisté avec succees ");
        alert.showAndWait();
        donnee.addAll(service.listerInscription());
        tblv_incription.setItems(donnee);
        tblv_incription.refresh();     */    
                   
                   
          

    }
    
}
