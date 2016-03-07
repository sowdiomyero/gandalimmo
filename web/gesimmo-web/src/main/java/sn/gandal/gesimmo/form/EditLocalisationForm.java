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
public class EditLocalisationForm extends BasicResponse {

    private String dateCreation;
    private String dateUpdated;
    private Long idLocalisation;
    @JsonProperty
    private String localisation;
    private String cleLocalite;
    @NotNull
    @NotEmpty
    private String nom;
    @NotNull
    @NotEmpty
    private String description;
    private String longitude;
    private String latitude;
    private String longitudeMin;
    private String longitudeMax;
    private String latitudeMin;
    private String latitudeMax;    

    @NotNull
    @NotEmpty
    private String type;
    private String coordonnees;
    private String nomLocaliteGoogle;

    private List<Localisation> rattachements = new ArrayList<Localisation>();
    private String rattachement;
    
// Pour les formulaires de modification
    
    private List<String> dType = new ArrayList<String>();

    private List<String> typeIncidentOuLocalite = new ArrayList<String>();
    private List<String> gravites = new ArrayList<String>();
    
    private Map<Long,String> responsables = new HashMap<Long,String>();    
    private List<User> listResponsables = new ArrayList<User>();
    private String responsable;
    
    private Map<Long,String> proprietaires = new HashMap<Long,String>();
    private String proprietaire;
    
    private User responsableAttribution;
//    private List<String> typeIncidentOuLocalite = new ArrayList<String>();
    private String dT;

    private String gravite;
    
    private String nbNiveaux;
    private String nbObjets;
    
    private String nbNiveauxEffectifs;
    private String nbObjetsEffectifs;
      
    private String oldRattachement;
    private String oldResponsable; 
    private String oldProprietaire; 
    private String oldZone;
    /**
     * La valeur doit être testé avec l'identifiant c'est à dire la claf de la map : key : idZone
     * La map contient key:value ici value correspond à optionLibelle la key à optionValue
     */
    private String zone;    
    private Map<Long,String> zones = new HashMap<Long,String>();
    
    private Map<String, String> listeCaracteristiques = new HashMap<String, String>();
    private Long[] caracteristique = new Long[]{};


    public String getLatitudeMin() {
        return latitudeMin;
    }

    public void setLatitudeMin(String latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public String getLatitudeMax() {
        return latitudeMax;
    }

    public void setLatitudeMax(String latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

    
    public String getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(String longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public String getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(String longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public EditLocalisationForm() {
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

    public List<String> getdType() {
        return dType;
    }

    public void setdType(List<String> dType) {
        this.dType = dType;
    }

    public String getdT() {
        return dT;
    }

    public void setdT(String dT) {
        this.dT = dT;
    }

    public List<String> getTypeIncidentOuLocalite() {
        return typeIncidentOuLocalite;
    }

    public void setTypeIncidentOuLocalite(List<String> typeIncidentOuLocalite) {
        this.typeIncidentOuLocalite = typeIncidentOuLocalite;
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

    public String getNbNiveauxEffectifs() {
        return nbNiveauxEffectifs;
    }

    public void setNbNiveauxEffectifs(String nbNiveauxEffectifs) {
        this.nbNiveauxEffectifs = nbNiveauxEffectifs;
    }

    public String getNbObjetsEffectifs() {
        return nbObjetsEffectifs;
    }

    public void setNbObjetsEffectifs(String nbObjetsEffectifs) {
        this.nbObjetsEffectifs = nbObjetsEffectifs;
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

    public String getOldProprietaire() {
        return oldProprietaire;
    }

    public void setOldProprietaire(String oldProprietaire) {
        this.oldProprietaire = oldProprietaire;
    }

    public Map<Long, String> getProprietaires() {
        return proprietaires;
    }

    public void setProprietaires(Map<Long, String> proprietaires) {
        this.proprietaires = proprietaires;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getOldZone() {
        return oldZone;
    }

    public void setOldZone(String oldZone) {
        this.oldZone = oldZone;
    }

    public List<String> getGravites() {
        return gravites;
    }

    public void setGravites(List<String> gavites) {
        this.gravites = gavites;
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

//    public void setZones(Map<Long, String> zones) {
//        this.zones = zones;
//    }
    
    public void setZones(List<Zone> zones) {
        for(Zone zn : zones){
            this.zones.put(zn.getIdZone(), zn.getNomZoneGoogle());
        }
    }

   

    public Long[] getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Long[] caracteristique) {
        this.caracteristique = caracteristique;
    }
    
    public Map<String, String> getListeCaracteristiques() {
        return listeCaracteristiques;
    }

    public void setListeCaracteristiques(Map<String, String> listeCaracteristiques) {
        this.listeCaracteristiques = listeCaracteristiques;
    }
    
    public void populateListeCaracteristiques(List<Caracteristique> listeCaracteristiques) {
        for(Caracteristique car : listeCaracteristiques)
            this.listeCaracteristiques.put(car.getIdCaracteristique().toString(), car.getNomCaracteristique());
    }

        public void populateZones(List<Zone> zones) {
        for(Zone car : zones)
            this.zones.put(car.getIdZone(), car.getNomZoneGoogle());
    }

   
    
}
