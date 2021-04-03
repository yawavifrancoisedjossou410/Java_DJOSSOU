/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Models.Classe;
import Models.Etudiant;
import Models.Inscription;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class InscriptionDao implements IDao<Inscription> {
    private Mysql mysql=new Mysql(); 
    private UserDao etudDao=new UserDao();  
    private ClasseDao clDao=new ClasseDao();


    
        private final String SQL_INSERT="INSERT INTO `inscription` ( `create_at`, `annee`, `classe_id`, `etudiant_id`) VALUES (?, ?, ?, ?);";
        private final String SQL_SELECT="SELECT classe.id as id_classe, user.id as id_user, inscription.id as id, inscription.annee, inscription.create_at FROM classe INNER JOIN inscription ON inscription.classe_id = classe.id INNER JOIN user ON inscription.etudiant_id = user.id";
    @Override
    public int insert(Inscription objet) {
        int id=0;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            mysql.initPreparedStatement(SQL_INSERT);
            //2-Preparer la requette
            mysql.getPs().setDate(1, Date.valueOf(objet.getCreateAt()));                     
            mysql.getPs().setString(2, objet.getAnnée()); 
            mysql.getPs().setInt(3, objet.getClasse().getId());       
            mysql.getPs().setInt(3, objet.getEtu().getId()); 
            //3-executer la requette //4-Recuperation des resultats
            mysql.executeMaj();
            ResultSet rs = mysql.getPs().getGeneratedKeys();
            rs.next();
            id=rs.getInt(1);
            mysql.fermerBD();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int update(Inscription objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscription> selectAll() {
                 List <Inscription> lInscription=null;
        //1-connexion a une BD
        mysql.connexionBD();
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect(SQL_SELECT);
        try {
            //4-Recuperation des resultats
            lInscription=new ArrayList<>();
            while (rs.next()){
                Inscription insc =new Inscription();
                //recupérer id que l'on stocke dans l'objet cl
                insc.setId(rs.getInt("id"));
                insc.setAnnée(rs.getString("annee")); 
                insc.setEtu(etudDao.selectById(rs.getInt("id_user")));   
                insc.setClasse(clDao.selectById(rs.getInt("id_classe")));
                lInscription.add(insc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
        
        return lInscription;
    }

    
}
