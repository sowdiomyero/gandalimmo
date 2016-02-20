/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Niveau;
import sn.gandal.gesimmo.modele.client.entities.User;


/**
 *
 * @author msall
 */
public interface INiveauMetier {
   
    public List<Niveau> findAllNiveaux();

    public User findNiveauById(Long idNiveau);

    public List<Niveau> findAllBatimentNiveauxByIdBatiment(Long idBatiment);

    public List<Niveau> findNiveauByCaracteristique(String caracteritique);

    public Niveau updateNiveau(Niveau niveau);

    public Niveau save(Niveau niveau);
}
