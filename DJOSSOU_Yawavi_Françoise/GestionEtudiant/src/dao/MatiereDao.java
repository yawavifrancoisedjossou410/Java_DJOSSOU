/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Models.Classe;
import Models.Matiere;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MatiereDao implements IDao<Matiere>   {
       private Mysql mysql=new Mysql();
    private final String SQL_SELECT_ALL_MATIERE="select * from matiere";
    private final String SQL_INSERT="INSERT INTO `matiere` (`name`) VALUES (?);";
    @Override
    public int insert(Matiere objet) {
        int id=0;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            mysql.initPreparedStatement(SQL_INSERT);
            //2-Preparer la requette
            System.out.println(objet.getName());
            mysql.getPs().setString(1, objet.getName());
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
    public int update(Matiere objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Matiere> selectAll() {
                 List <Matiere> lMatiere=null;
        //1-connexion a une BD
        mysql.connexionBD();
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect(SQL_SELECT_ALL_MATIERE);
        try {
            //4-Recuperation des resultats
            lMatiere=new ArrayList<>();
            while (rs.next()){
                Matiere mat =new Matiere();
                //recupérer id que l'on stocke dans l'objet cl
                mat.setId(rs.getInt("id"));
                mat.setName(rs.getString("name"));
                lMatiere.add(mat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
        
        return lMatiere;
    }
    
}
