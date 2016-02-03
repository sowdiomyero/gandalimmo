/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.dto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.SiteLocalite;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;

/**
 *
 * @author DYSOW
 */
public class LocalisationDTO {
    

    private Long idLocalisation;    
    private String localisation;
    private String cleLocalite;

    private String nom;

    private String description;
    private String longitude;
    private String latitude;       

    private String type;

    private List<LocalisationDTO> rattachements = new ArrayList<LocalisationDTO>();
    
    private String rattachement;

    private String responsable;
    
    private String dT;

    private String gravite;
    
    private String nbNiveaux;
    private String nbObjets;
    
    private String dateCreation;
    private String dateUpdated;
         

    public LocalisationDTO(Localisation loc) {
        this.idLocalisation = loc.getIdLocalisation();
        this.nom = loc.getNomLocalisable();
        this.cleLocalite = loc.getClef();
        this.dateCreation = DateFormat.getInstance().format(loc.getDateCreation());
        this.dateUpdated = DateFormat.getInstance().format(loc.getDateUpdated());
        
        if(loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_INCIDENT)){
            this.gravite = ((ObjetIncident)loc).getGravite();
        }else if (loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_SITE)){
            this.nbObjets = String.valueOf(((SiteLocalite)loc).getNbObjets());
        }else if (loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_BATIMENT)){
            this.nbNiveaux = String.valueOf(((BatimentLocalite)loc).getNbNiveaux());
                }
        this.type = loc.getType();
        this.dT=loc.getDType();
        this.description=loc.getDescription();
        this.longitude = loc.getLongitude();
        this.latitude = loc.getLatitude();
        this.rattachement = (loc.getParentLocalisation() != null ? loc.getParentLocalisation().getNomLocalisable(): "Non Rattaché");
        
    }

   

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

   
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
  

    public List<LocalisationDTO> getRattachements() {
        return rattachements;
    }

    public void setRattachements(List<LocalisationDTO> rattachements) {
        this.rattachements = rattachements;
    }

    public String getRattachement() {
        return rattachement;
    }

    public void setRattachement(String rattachement) {
        this.rattachement = rattachement;
    }

   

    public String getdT() {
        return dT;
    }

    public void setdT(String dT) {
        this.dT = dT;
    }

    

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public String getNbNiveaux() {
        return nbNiveaux;
    }

    public void setNbNiveaux(String nbNiveaux) {
        this.nbNiveaux = nbNiveaux;
    }

    public String getNbObjets() {
        return nbObjets;
    }

    public void setNbObjets(String nbObjets) {
        this.nbObjets = nbObjets;
    }

   

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }


    public String getCleLocalite() {
        return cleLocalite;
    }

    public void setCleLocalite(String cleLocalite) {
        this.cleLocalite = cleLocalite;
    }
   
}
