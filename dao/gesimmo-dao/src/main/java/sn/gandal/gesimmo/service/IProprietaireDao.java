/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import sn.gandal.gesimmo.modele.client.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Proprietaire;

/**
 *
 * @author sniang
 */
public interface IProprietaireDao extends CrudRepository<Proprietaire, Long> {

    public boolean isProprietaireWithEmailExist(String emailAdresse);

    public List<Proprietaire> findAllProprietaires();

    public Proprietaire findProprietaireById(Long idProprietaire);

    public Proprietaire findProprietaireByEmail(String userMail);

    public Proprietaire findProprietaireByIdCompte(Long idCompte);

    public void updateProprietaire(Proprietaire user);

    public List<Proprietaire> findAllProprietairesWithoutProprietaireConnected(String login);

    public boolean isProprietaireWithTelExist(String tel);

    public boolean isProprietaireWithTelExist(String tel, Long idProprietaire);

    public boolean isProprietaireWithMailExist(String mail, Long idProprietaire);

    public Proprietaire isProprietaireWithLoginAndEmailExist(String login, String email);

}
