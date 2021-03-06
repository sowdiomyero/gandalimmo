/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Pattern;

/**
 *
 * @author DYSOW
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NiveauForm {
 
    Long idLocalisation;
    String libelleNiveau;
    //@Pattern(regexp = "{A-Za-z0-9}*")
    String superficieNiveau;
  
    Map<String, String> listeCaracteristiques = new HashMap<String, String>();
    Long[] caracteristique = new Long[]{};
    
    

    
    Map<String, String> etats =  new HashMap<String, String>();
    Map<String, String> levels=  new HashMap<String, String>();
    
    String etat;
    String level;

    public NiveauForm() {
    }

    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long idLocalisation) {
        this.idLocalisation = idLocalisation;
    }

    public String getLibelleNiveau() {
        return libelleNiveau;
    }

    public void setLibelleNiveau(String libelleNiveau) {
        this.libelleNiveau = libelleNiveau;
    }

    public String getSuperficieNiveau() {
        return superficieNiveau;
    }

    public void setSuperficieNiveau(String superficieNiveau) {
        this.superficieNiveau = superficieNiveau;
    }
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Map<String, String> getEtats() {
        return etats;
    }

    public void setEtats(Map<String, String> etats) {
        this.etats = etats;
    }

    public Map<String, String> getLevels() {
        return levels;
    }

    public void setLevels(Map<String, String> levels) {
        this.levels = levels;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

   

    public Map<String, String> getListeCaracteristiques() {
        return listeCaracteristiques;
    }

    public void setListeCaracteristiques(Map<String, String> listeCaracteristiques) {
        this.listeCaracteristiques = listeCaracteristiques;
    }

    public Long[] getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Long[] caracteristique) {
        this.caracteristique = caracteristique;
    }

    

}
