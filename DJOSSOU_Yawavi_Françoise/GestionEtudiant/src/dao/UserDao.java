/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Models.AC;
import Models.Classe;
import Models.Etudiant;
import Models.Professeur;
import Models.RP;
import Models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class UserDao implements IDao<User>  {
    //REQUETTE SQL
    private final String SQL_SELECT_USER_LOGIN="select * from user where login=? and pwd=?";  
    private final String SQL_ID="select * from user where id=?";

    private final String SQL_SELECT_ETUDIANT_MATRICULE="select * from user where matricule=?";
    private final String SQL_INSERT="INSERT INTO `user` ( `nomComplet`, `login`, `pwd`, `grade`, `matieres`, `tuteur`, `matricule`, `type`)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SQL_SELECT_ETUDIANT_CLASSE="select u.id,u.matricule,u.nomComplet,u.tuteur from user u, inscription i"
                                                                +" where u.id=i.etudiant_id "
                                                                +" and i.classe_id=? and i.annee=?"
                                                                +" and u.type='Etudiant'";
    private Mysql mysql=new Mysql();
    @Override
    public int insert(User objet) {
         int id=0;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            mysql.initPreparedStatement(SQL_INSERT);
            mysql.getPs().setString(1, objet.getNomComplet());
            mysql.getPs().setString(8, objet.getType());
            //2-Preparer la requette
            if(objet.getType().compareTo("Etudiant")==0){
               
                mysql.getPs().setString(2, null);
                mysql.getPs().setString(3, null);
                mysql.getPs().setString(4, null);
                //mysql.getPs().setString(5 null);
                //ici on fait le dowcasting
                mysql.getPs().setString(6, ((Etudiant)objet).getTuteur());
                mysql.getPs().setString(7, ((Etudiant)objet).getMatricule());
            }else{
                if(objet.getType().compareTo("Professeur")==0){
                mysql.getPs().setString(1, objet.getNomComplet());
                mysql.getPs().setString(2, null);
                mysql.getPs().setString(3, null);
                mysql.getPs().setString(4, ((Professeur)objet).getGrade());
                //mysql.getPs().setString(5 null);
                //ici on fait le dowcasting
                mysql.getPs().setString(6, null);
                mysql.getPs().setString(7, null);
                }else{
                    //RP OU AC
                    mysql.getPs().setString(2, objet.getLogin());
                    mysql.getPs().setString(3, objet.getPwd());
                    mysql.getPs().setString(3, null);
                    mysql.getPs().setString(4, ((Professeur)objet).getGrade());
                    //mysql.getPs().setString(5 null);
                    //ici on fait le dowcasting
                    mysql.getPs().setString(6, null);
                    mysql.getPs().setString(7, null);
                     }
            }
              
            //3-executer la requette //4-Recuperation des resultats
            mysql.executeMaj(SQL_INSERT);
            ResultSet rs = mysql.getPs().getGeneratedKeys();
            rs.next();
            id=rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int update(User objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public User selectUserByLoginAndPwd(String login ,String pwd){
          User userConnect=null;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            mysql.initPreparedStatement(SQL_SELECT_USER_LOGIN);
            mysql.getPs().setString(1,login);
            mysql.getPs().setString(2,pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect();
        try {
            //4-Recuperation des resultats
            if(rs.next()){
                String type=rs.getString("type");
                if (type.compareTo("AC")==0){
                    userConnect= new AC();
                }
                 if (type.compareTo("RP")==0){
                    userConnect= new RP();
                }
                userConnect.setId(rs.getInt("id"));
                userConnect.setLogin(rs.getString("login"));
                userConnect.setPwd(rs.getString("pwd"));
                userConnect.setNomComplet(rs.getString("nomComplet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
            
        return userConnect;
    }
    
    public Etudiant selectEtudiant (String matricule){
      Etudiant etu=null;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            mysql.getPs().setString(1, matricule);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect(SQL_SELECT_ETUDIANT_MATRICULE);
        try {
            //4-Recuperation des resultats
            if(rs.next()){
                etu =new Etudiant();
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule"));
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
        
        return etu;
    }
    public List<Etudiant> selectEtudiantByClasseParAnnee(Classe cl,String annee) {
        List<Etudiant> lEtudiant=null;
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            
            mysql.initPreparedStatement(SQL_SELECT_ETUDIANT_CLASSE);
            mysql.getPs().setInt(1, cl.getId());
            mysql.getPs().setString(2, annee);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect();
        try {
            lEtudiant=new ArrayList <>();
            //4-Recuperation des resultats
            while(rs.next()){
                Etudiant etu  =new Etudiant();
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule"));
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
                lEtudiant.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
        
        return lEtudiant;
        
    }
     public Etudiant selectById(int id) {
                 Etudiant etu  =new Etudiant();
        //1-connexion a une BD
        mysql.connexionBD();
        try {
            
            mysql.initPreparedStatement(SQL_ID);
            mysql.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //3-executer la requette 
            ResultSet rs = mysql.executeSelect();
        try {
              if(rs.first()){
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule"));
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
            }
              if(etu!=null){
                    return etu;
                }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            mysql.fermerBD();
        
        return null;        
}
}
