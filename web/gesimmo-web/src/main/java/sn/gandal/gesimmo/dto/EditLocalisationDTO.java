/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.User;

/**
 *
 * @author gandal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditLocalisationDTO extends BasicResponse{
    
    private Long idLocalisation;

    @NotNull
    @NotEmpty
    private String nom;
    @NotNull
    @NotEmpty
    private String description;
    
    @NotNull
    @NotEmpty
    private String type;
    private String dT;
    
    private String gravite;
    
    private Long rattachement;
    private Long oldRattachement;

    private Long responsable;
    private Long oldResponsable;    

    
 
    

    private Long proprietaire;
    private Long oldProprietaire;
    
    private Long zone;
    private Long oldZone;
    
    private Long[] caracteristique;

    public EditLocalisationDTO() {
    }

    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long idLocalisation) {
        this.idLocalisation = idLocalisation;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   
    public Long getRattachement() {
        return rattachement;
    }

    public void setRattachement(Long rattachement) {
        this.rattachement = rattachement;
    }


    public Long getResponsable() {
        return responsable;
    }

    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }

    public Long getOldResponsable() {
        return oldResponsable;
    }

    public void setOldResponsable(Long oldResponsable) {
        this.oldResponsable = oldResponsable;
    }
    public String getdT() {
        return dT;
    }

    public void setdT(String dT) {
        this.dT = dT;
    }

    public Long getOldRattachement() {
        return oldRattachement;
    }

    public void setOldRattachement(Long oldRattachement) {
        this.oldRattachement = oldRattachement;
    }



    public Long getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Long proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public Long getOldProprietaire() {
        return oldProprietaire;
    }

    public void setOldProprietaire(Long oldProprietaire) {
        this.oldProprietaire = oldProprietaire;
    }

    public Long getZone() {
        return zone;
    }

    public void setZone(Long zone) {
        this.zone = zone;
    }

    public Long getOldZone() {
        return oldZone;
    }

    public void setOldZone(Long oldZone) {
        this.oldZone = oldZone;
    }

    public Long[] getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Long[] caracteristiques) {
        this.caracteristique = caracteristiques;
    }
     
    
}
