/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ddiaw
 */
@Entity
@Table(name = "caracteristique")
@NamedQueries({
    @NamedQuery(name = Caracteristique.FIND_CARACTERISTIQUE_BY_ID, query = "SELECT i FROM Caracteristique i where i.idCaracteristique = :idCaracteristique"),
    @NamedQuery(name = Caracteristique.FIND_ALL_CARACTERISTIQUE, query = "SELECT i FROM Caracteristique i"),    
    @NamedQuery(name = Caracteristique.FIND_CARACTERISTIQUE_BY_LIBELLE, query = "SELECT i FROM Caracteristique i where i.libelle = :libelle"),
    @NamedQuery(name = Caracteristique.FIND_CARACTERISTIQUE_IN, query = "SELECT i FROM Caracteristique i where i.idCaracteristique IN (:arrayIn)"),
    @NamedQuery(name = Caracteristique.FIND_CARACTERISTIQUE_BY_NAME, query = "SELECT i FROM Caracteristique i where i.nomCaracteristique = :nomCaracteristique")
    
})

public class Caracteristique extends AbstractDateEntity implements Serializable {

    public static final String FIND_CARACTERISTIQUE_BY_ID = "findCaracteristiqueById";
    public static final String FIND_ALL_CARACTERISTIQUE = "findAllCaracteristiques";
    public static final String FIND_CARACTERISTIQUE_BY_LIBELLE = "findCaracteristiquesByLibelle";
    public static final String FIND_CARACTERISTIQUE_BY_NAME = "findCaracteristiquesByName";
    public static final String FIND_CARACTERISTIQUE_IN = "findCaracteristiquesIn";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_indicateur")
    private Long idCaracteristique;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "nom_indicateur")
    private String nomCaracteristique;
    
    @ManyToMany(mappedBy = "caracteristiques")
    private List<Localisation> localisations;

    @ManyToMany(mappedBy = "caracteristiques")
    private List<Niveau> niveaux;
   
    public Caracteristique() {
    }

    public Long getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(Long idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

   
    public String getNomCaracteristique() {
        return nomCaracteristique;
    }

    public void setNomCaracteristique(String nomCaracteristique) {
        this.nomCaracteristique = nomCaracteristique;
    }

    public List<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }

    public List<Niveau> getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(List<Niveau> niveaux) {
        this.niveaux = niveaux;
    }

    

    @Override
    public String toString() {
        return this.libelle;
    }

 

}
