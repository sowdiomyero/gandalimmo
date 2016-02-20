/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.INiveauMetier;
import sn.gandal.gesimmo.modele.client.entities.Niveau;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.service.INiveauDao;

/**
 *
 * @author DYSOW
 */
@Service
public class NiveauMetierImpl implements INiveauMetier{

     @Autowired
     INiveauDao dao;

    @Override
    public List<Niveau> findAllNiveaux() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findNiveauById(Long idNiveau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Niveau> findAllBatimentNiveauxByIdBatiment(Long idBatiment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Niveau> findNiveauByCaracteristique(String caracteritique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Niveau updateNiveau(Niveau niveau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Niveau save(Niveau niveau) {
        return dao.saveNiveau(niveau);
    }
     
    
    
}
