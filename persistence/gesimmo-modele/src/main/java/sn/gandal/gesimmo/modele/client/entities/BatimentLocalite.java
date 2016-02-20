/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import sn.gandal.gesimmo.modele.client.entities.Niveau.ETAGE;

/**
 *
 * @author dysow
 */
@Entity
@DiscriminatorValue(value = TableConfig.DTYPE_BATIMENT)
@NamedQueries({
    @NamedQuery(name = BatimentLocalite.FIND_ALL_BATIMENT_BY_TYPE_AND_ETAT, query = "SELECT l FROM BatimentLocalite l where l.etat = :etat and l.type = :type"),
    @NamedQuery(name = BatimentLocalite.FIND_ALL_BATIMENT_BY_ETAT, query = "SELECT l FROM BatimentLocalite l where l.etat = :etat"),
    @NamedQuery(name = BatimentLocalite.FIND_BATIMENT_BY_NAME, query = "SELECT l FROM BatimentLocalite l where l.nomLocalisable = :nom")})

public class BatimentLocalite extends Localisation {

    // Noms requetes nommees
    public static final String FIND_ALL_BATIMENT_BY_TYPE_AND_ETAT = "findAllBatimentByTypeAndEtat";
    public static final String FIND_ALL_BATIMENT_BY_ETAT = "findAllBatimentByEtat";
    public static final String FIND_BATIMENT_BY_NAME = "findBatimentByName";
    public static final String FIND_ALL_BATIMENTS = "findAllBatiments";
    // Fin declaration des noms des requetes nommees
    
//    @Column(name = "nb_niveaux", length = 2)
//    private Integer nbNiveaux;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batiment")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Niveau> niveaux = new ArrayList<Niveau>();

    public BatimentLocalite(String tout) {
        super.setNomLocalisable(tout);
    }

    public static enum TYPE {
        LOCATION, LOCATION_VENTE, BUREAU
    }

//    public Integer getNbNiveaux() {
//        return nbNiveaux;
//    }
//
//    public void setNbNiveaux(Integer nbNiveaux) {
//        this.nbNiveaux = nbNiveaux;
//    }
//   

    public BatimentLocalite() {
        super();
    }

    public String getDType() {
        return TableConfig.DTYPE_BATIMENT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeToString() {

        return type;
    }

    public static List<String> getAllTypes() {
        List<BatimentLocalite.TYPE> res = Arrays.asList(BatimentLocalite.TYPE.values());
        List<String> response = new ArrayList<String>();
        for (BatimentLocalite.TYPE value : res) {
            response.add(value.name());
        }
        return response;
    }

    @Override
    public List<String> getSpecificTypes() {
       List<BatimentLocalite.TYPE> res = Arrays.asList(BatimentLocalite.TYPE.values());
        List<String> response = new ArrayList<String>();
        for (BatimentLocalite.TYPE value : res) {
            response.add(value.name());
        }
        return response;
    }

    public List<Niveau> getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(List<Niveau> niveaux) {
        this.niveaux = niveaux;
    }
    
    public Map<String, String> getNiveauxSaisis(){
       Map<String, String> result = new HashMap<String, String>();
       if(getNiveaux() != null && getNiveaux().size()>0 ){
           for(Niveau nv : getNiveaux()){
               if(nv.getEtage() != null)
                    result.put(nv.getEtage().getKey(), nv.getEtage().getLabel());
           }
       }
       return result;
    }
    
    public Map<String, String> exclureNiveauxSaisis(){
      Map<String, String> result = new LinkedHashMap<String, String>();
      Map<String, String> niveauxSaisis = getNiveauxSaisis();
       for(ETAGE etage : Niveau.ETAGE.values()){
           if(! niveauxSaisis.containsKey(etage.getKey()))
                result.put(etage.getKey(), etage.getLabel());
       }
       
       return result;
    }
    
   public int getNbNiveauxSaisis(){
       if(getNiveaux() != null && getNiveaux().size()>0 ){
           return getNiveaux().size();
       } 
       return 0;
    }

}
