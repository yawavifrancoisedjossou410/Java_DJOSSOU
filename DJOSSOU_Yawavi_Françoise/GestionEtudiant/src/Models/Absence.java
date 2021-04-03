/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Absence {
    private int id;
    private LocalDate createAt;
    private boolean absence;
    private Etudiant etudiant;
    private Professeur professeur;
    private Matiere matiere;

    public Absence(int id, LocalDate createAt, boolean absence, Etudiant etudiant, Professeur professeur, Matiere matiere) {
        this.id = id;
        this.createAt = createAt;
        this.absence = absence;
        this.etudiant = etudiant;
        this.professeur = professeur;
        this.matiere = matiere;
    }


    @Override
    public String toString() {
        return "Absence{" + "createAt=" + createAt + ", absence=" + absence + ", etudiant=" + etudiant + ", professeur=" + professeur + ", matiere=" + matiere + '}';
    }

    public Absence(LocalDate createAt, boolean absence, Etudiant etudiant, Professeur professeur, Matiere matiere) {
        this.createAt = createAt;
        this.absence = absence;
        this.etudiant = etudiant;
        this.professeur = professeur;
        this.matiere = matiere;
    }
    

    /**
     * @return the createAt
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the absence
     */
    public boolean isAbsence() {
        return absence;
    }

    /**
     * @param absence the absence to set
     */
    public void setAbsence(boolean absence) {
        this.absence = absence;
    }

    /**
     * @return the etudiant
     */
    public Etudiant getEtudiant() {
        return etudiant;
    }

    /**
     * @param etudiant the etudiant to set
     */
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    /**
     * @return the professeur
     */
    public Professeur getProfesseur() {
        return professeur;
    }

    /**
     * @param professeur the professeur to set
     */
    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    /**
     * @return the matiere
     */
    public Matiere getMatiere() {
        return matiere;
    }

    /**
     * @param matiere the matiere to set
     */
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
}
