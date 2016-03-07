/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.modele.client.entities.Caracteristique;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.entities.Zone;

/**
 *
 * @author dysow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatimentForm extends BasicResponse {

 
    private Long idLocalisation;
    @JsonProperty
    private String localisation;
    @NotNull
    @NotEmpty
    private String cleLocalite;
    @NotNull
    @NotEmpty
    private String nom;
    @NotNull
    @NotEmpty
    private String description;
    
    private String longitude;
    private String latitude;  

    @NotNull
    @NotEmpty
    private String type;
    private String coordonnees;
    private String nomLocaliteGoogle;

    private List<Localisation> rattachements = new ArrayList<Localisation>();
    private List<String> typesBatiment = new ArrayList<String>();
    private List<String> etatsBatiment = new ArrayList<String>();
    private String etatBatiment = "";
    private String typeBatiment = "";
    private String rattachement;
    
// Pour les formulaires de modification
    
    private Map<Long,String> responsables = new HashMap<Long,String>();
    private List<User> listResponsables = new ArrayList<User>();
    private String responsable;
    
    private User responsableAttribution;
    
    private String dT="BATIMENT";

    
    private String nbNiveaux;
      
    private String oldRattachement;
    private String oldResponsable; 
    private String oldZone;
    
    private String zone;    
    private Map<Long,String> zones = new HashMap<Long,String>();

    private Long[] caracteristique = new Long[]{};    
    private Map<String,String> caracteristiques = new HashMap<String,String>();
    
    
    public BatimentForm() {
        
    }

        

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
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

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getNomLocaliteGoogle() {
        return nomLocaliteGoogle;
    }

    public void setNomLocaliteGoogle(String nomLocaliteGoogle) {
        this.nomLocaliteGoogle = nomLocaliteGoogle;
    }

    public List<Localisation> getRattachements() {
        return rattachements;
    }

    public void setRattachements(List<Localisation> rattachements) {
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
   
    public String getNbNiveaux() {
        return nbNiveaux;
    }

    public void setNbNiveaux(String nbNiveaux) {
        this.nbNiveaux = nbNiveaux;
    }


    public Map<Long, String> getResponsables() {
        return responsables;
    }

    public void setResponsables(Map<Long, String> responsables) {
        this.responsables = responsables;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<User> getListResponsables() {
        return listResponsables;
    }

    public void setListResponsables(List<User> listResponsables) {
        this.listResponsables = listResponsables;
    }

    public User getResponsableAttribution() {
        return responsableAttribution;
    }

    public void setResponsableAttribution(User responsableAttribution) {
        this.responsableAttribution = responsableAttribution;
    }

    public String getOldRattachement() {
        return oldRattachement;
    }

    public void setOldRattachement(String oldRattachement) {
        this.oldRattachement = oldRattachement;
    }

    public String getOldResponsable() {
        return oldResponsable;
    }

    public void setOldResponsable(String oldResponsable) {
        this.oldResponsable = oldResponsable;
    }

    public String getOldZone() {
        return oldZone;
    }

    public void setOldZone(String oldZone) {
        this.oldZone = oldZone;
    }

    
    public String getCleLocalite() {
        return cleLocalite;
    }

    public void setCleLocalite(String cleLocalite) {
        this.cleLocalite = cleLocalite;
    }

    public void setZone(String zn) {
       this.zone =zn;
    }

    public String getZone() {
        return zone;
    }

    public Map<Long, String> getZones() {
        return zones;
    }
    
    public void setZones(List<Zone> zones) {
        for(Zone zn : zones){
            this.zones.put(zn.getIdZone(), zn.getNomZoneGoogle());
        }
    }

    public List<String> getTypesBatiment() {
        return typesBatiment;
    }

    public void setTypesBatiment(List<String> typeBatiment) {
        this.typesBatiment = typeBatiment;
    }
    
    public String getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    public String getEtatBatiment() {
        return etatBatiment;
    }

    public void setEtatBatiment(String etatBatiment) {
        this.etatBatiment = etatBatiment;
    }

     public List<String> getEtatsBatiment() {
        return etatsBatiment;
    }

    public void setEtatsBatiment(List<String> etatBatiment) {
        this.etatsBatiment = etatBatiment;
    }

    public Long[] getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Long[] caracteristique) {
        this.caracteristique = caracteristique;
    }

    public Map<String, String> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(Map<String, String> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    
    public void populateListeCaracteristiques(List<Caracteristique> listeCaracteristiques) {
        for(Caracteristique car : listeCaracteristiques)
            this.caracteristiques.put(car.getIdCaracteristique().toString(), car.getNomCaracteristique());
    }
    

   
    
}
