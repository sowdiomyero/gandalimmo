/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "proprietaire")
@NamedQueries({
    @NamedQuery(name = Proprietaire.FIND_USER_BY_EMAIL, query = "SELECT u FROM Proprietaire u where u.userMail=:userMail"),
    @NamedQuery(name = Proprietaire.FIND_USER_BY_ID, query = "SELECT u FROM Proprietaire u where u.idUser=:idUser"),
    @NamedQuery(name = Proprietaire.FIND_USER_BY_ID_COMPTE, query = "SELECT u FROM Proprietaire u where u.idCompte=:idCompte"),
    @NamedQuery(name = Proprietaire.FIND_ALL_USERS, query = "SELECT u FROM Proprietaire u"),})
public class Proprietaire extends Personne {

    public static final String FIND_USER_BY_EMAIL = "findProprietaireByEmail";
    public static final String FIND_ALL_USERS = "findAllTProprietaires";

    public static final String FIND_USER_BY_ID = "findProprietaireById";
    public static final String FIND_USER_BY_ID_COMPTE = "findProprietaireByIdCompte";


    @OneToMany( cascade = CascadeType.ALL, mappedBy = "proprietaire")
    private List<Localisation> localisations;


     public Proprietaire() {
        super();
    }

    /**
     * @return the userMail
     */
    public List<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }



}
