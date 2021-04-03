/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Models.Classe;
import Models.Etudiant;
import Models.Inscription;
import Models.Matiere;
import Models.Professeur;
import Models.User;
import dao.ClasseDao;
import dao.InscriptionDao;
import dao.MatiereDao;
import dao.UserDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class GestionEtudiantImpl implements IGestionEtudiant {
    private LocalDate date;
    private ClasseDao daoClasse = new ClasseDao();
    private MatiereDao daoMatiere= new MatiereDao();
    private UserDao daoUser = new UserDao();
    private InscriptionDao daoIns =new InscriptionDao();
    @Override
    public List<Classe> listerClasseEnseignat(Professeur prof) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean inscrireEtudiant(Etudiant etu, Classe cl,String annee) {
        if(etu.getId()==0){

            int id=daoUser.insert(etu);
            String mat=generateMatricule(id);
            etu.setMatricule(mat);
            etu.setId(id);
        }
        Inscription ins = new Inscription(annee, etu,cl);
        return daoIns.insert(ins)!=0;
    }
    
    private String generateMatricule(int id){
        int y=date.now().getYear();
        String matricule="MAT"+y+"00";
        return matricule;
    }

    @Override
    public int addClasse(Classe cl) {
        return daoClasse.insert(cl);
    }

  
    @Override
    public List<String> listerModuleParClasseUnProf(Professeur prof, Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean affecterProfesseur(Professeur prof, Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierClasse(Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User seConnecter(String login, String pwd) {
        return daoUser.selectUserByLoginAndPwd(login,pwd);
    }

    @Override
    public List<Classe> listerClasse() {
       return daoClasse.selectAll(); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Inscription> listerInscription() {
       return daoIns.selectAll(); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public boolean rechercherClasseByLibelle(String libelle) {
        return daoClasse.selectClasseByLibelle(libelle)!=null;
    }
    public Classe rechercheClasseLibelle(String libelle){
    return daoClasse.selectClasseByLibelle(libelle);
    }

    @Override
    public boolean rechercherEtudiantParMatricule(String matricule) {
     return daoUser.selectEtudiant(matricule)!=null;
    }

    @Override
    public List<Etudiant> listerEtudiantParClasse(Classe cl, String annee) {
        return daoUser.selectEtudiantByClasseParAnnee(cl, annee);
    }

    @Override
    public List<String> getAnneeScolaire() {
        List<String> lAnneeScolaire = new ArrayList<>();
        return lAnneeScolaire;
    }
    public List<Matiere> listerMatiere() {
       return daoMatiere.selectAll();//To change body of generated methods, choose Tools | Templates.
    }
   
    public int addMatiere(Matiere mat) {
        return daoMatiere.insert(mat);
    }
    
}
