/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.controller.v1;

import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.NiveauForm;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Caracteristique;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.Niveau;
import sn.gandal.gesimmo.modele.client.entities.Niveau.ETAT;

/**
 *
 * @author DYSOW
 */
@Controller(value = "niveauController")
@RequestMapping(value = "/niveau")
public class NiveauController {
    
    
     Logger LOG = Logger.getLogger(LocalisationBrowser.class.getName());
    
    @Autowired
    GesImmoServiceManager manager;
    
    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BasicResponse addNewNiveau(@RequestBody @Valid NiveauForm niveauForm, Model model) {
    
    BasicResponse response = new BasicResponse();

    BatimentLocalite batiment = (BatimentLocalite) manager.getLocalisationService().getLocalisationById(niveauForm.getIdLocalisation());
    
    Niveau niveauBean = new Niveau();
    Long[] listCaracteristiques = niveauForm.getCaracteristique();
    List<Caracteristique> caractrtq = manager.getLocalisationService().findCaracteristiquesFromList(listCaracteristiques);
    niveauBean.setCaracteristiques(caractrtq);
    niveauBean.setBatiment(batiment);
    niveauBean.setEtage(niveauForm.getLevel());
    niveauBean.setEtat(ETAT.valueOf(niveauForm.getEtat()));
//    niveauBean.setCamera(niveauForm.getCamera());
//    niveauBean.setExtincteur(niveauForm.getExtincteur());
//    niveauBean.setWifi(niveauForm.getWifi());
//    niveauBean.setAscenseur(niveauForm.getAscenseur());
    niveauBean.setLibelle(niveauForm.getLibelleNiveau());
    niveauBean.setSuperficie(niveauForm.getSuperficieNiveau());
    
    manager.getNiveauService().save(niveauBean);
    
    response.setMsg("Valeurs reçues avec succès");
    response.setResultat(BasicResponse.RETOUR_OK);
    return response;
    }
}
