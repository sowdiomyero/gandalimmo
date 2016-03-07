/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;
import sn.gandal.gesimmo.modele.client.entities.Proprietaire;
import sn.gandal.gesimmo.service.IProprietaireDao;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class ProprietaireDaoImpl implements IProprietaireDao {

@PersistenceContext
 EntityManager em;

    Logger LOG = Logger.getLogger(ProprietaireDaoImpl.class.getName());

    public <S extends Proprietaire> S save(S s) {
       return em.merge(s);
    }

    public <S extends Proprietaire> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Proprietaire findOne(Long id) {
       return em.find(Proprietaire.class, id);
    }
    public boolean exists(Long id) {
      return (em.find(Proprietaire.class, id).getIdUser()>0);
    }

    public Iterable<Proprietaire> findAll() {
        return em.createQuery("Select * from Proprietaire").getResultList();
    }

    public Iterable<Proprietaire> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
         return em.createNamedQuery("Select * from Proprietaire").getMaxResults();
    }

    public void delete(Long id) {
        em.remove(em.find(Proprietaire.class, id));
    }

    public void delete(Proprietaire t) {
        em.remove(t);
    }

    public void delete(Iterable<? extends Proprietaire> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Proprietaire> findAllProprietaires() {
        return (List<Proprietaire>) em.createNamedQuery(Proprietaire.FIND_ALL_USERS).getResultList();
    }
   
   

    @Override
    public boolean isProprietaireWithEmailExist(String emailAdresse) {
        List<Proprietaire> users=null;
        try{
            users=(List<Proprietaire>) em.createQuery("select u from Proprietaire u where u.userMail=:userMail").setParameter("userMail", emailAdresse)
                    .getResultList();
        }catch(Exception ex){
         System.out.println("Exception survenue dans la methode isProprietaireWithEmailExist  ["+ex.getMessage()+"]");
            return false;
        }
        if (users !=null && users.size()>0)
            return true;
        else
            return false;
    }
//Methode qui retourne un utilisateur à partir de son id
    public Proprietaire findProprietaireById(Long idProprietaire) {
      List<Proprietaire> users =    em.createNamedQuery(Proprietaire.FIND_USER_BY_ID,Proprietaire.class)
          .setParameter("idUser",idProprietaire ).getResultList();
      Proprietaire user= users.get(0);
        return user;
      }
//Methode qui retourne un utilisateur à partir de son adresse email
    public Proprietaire findProprietaireByEmail(String userMail) {
       List<Proprietaire> users =   em.createNamedQuery(Proprietaire.FIND_USER_BY_EMAIL,Proprietaire.class)
          .setParameter("userMail",userMail ).getResultList();
       Proprietaire user= users.get(0);
        return user; 
    }
//Methode de mise à jour d'un utilisateur
    public void updateProprietaire(Proprietaire editProprietaire) {
       Proprietaire user =em.merge(editProprietaire);
        System.out.println(user.getCompte().getPassword());
        
    }

    public Proprietaire findProprietaireByIdCompte(Long idCompte) {
    List<Proprietaire> users =   em.createNamedQuery(Proprietaire.FIND_USER_BY_ID_COMPTE,Proprietaire.class)
          .setParameter("idCompte",idCompte ).getResultList();
       Proprietaire user= users.get(0);
        return user; 
    }

    public List<Proprietaire> findAllProprietairesWithoutProprietaireConnected(String login) {   
         List<Proprietaire> users=null;
        try{
            users=(List<Proprietaire>) em.createQuery("select u from Proprietaire u where u.compte.login <>'"+login+"'")
                    .getResultList();
        }catch(Exception ex){
            return null;
        }
        if (users !=null && users.size()>0)
            return users;
        else
            return null;
    }

    @Override
    public boolean isProprietaireWithTelExist(String tel) {
        List<Proprietaire> users=null;
        try{
            users=(List<Proprietaire>) em.createQuery("select u from Proprietaire u where u.userPhone=:userPhone").setParameter("userPhone", tel)
                    .getResultList();
        }catch(Exception ex){
         System.out.println("######################### Exception survenue dans la methode isProprietaireWithTelExist  ["+ex.getMessage()+"]");
            return false;
        }
        if (users !=null && users.size()>0)
            return true;
        else
            return false;
    }

    public boolean isProprietaireWithTelExist(String tel, Long idProprietaire) {
         List<Proprietaire> users = (List<Proprietaire>) em.createQuery("Select u from Proprietaire u where u.userPhone =:userPhone and u.idUser <>:idProprietaire")
                .setParameter("userPhone", tel).setParameter("idProprietaire", idProprietaire).getResultList();
        if (users !=null && users.size()>0)
            return true;
        else
            return false;
    }

    public boolean isProprietaireWithMailExist(String mail, Long idProprietaire) {
        List<Proprietaire> users = (List<Proprietaire>) em.createQuery("Select u from Proprietaire u where u.userMail =:userMail and u.idUser <>:idProprietaire")
                .setParameter("userMail", mail).setParameter("idProprietaire", idProprietaire).getResultList();
        if (users !=null && users.size()>0)
            return true;
        else
            return false;
    }

    public Proprietaire isProprietaireWithLoginAndEmailExist(String login, String email) {
        Proprietaire user = null;
        user =(Proprietaire) em.createQuery("select u from Proprietaire u where u.userMail=:userMail").setParameter("userMail", email).getSingleResult();
        if(user != null){
            if(user.getCompte() != null){
                if(user.getCompte().getLogin().equals(login)){
                    return user;
                }
                return null;
            }
            return null;
        }
        return null;
    }
 
    
    
    
    
    
}
