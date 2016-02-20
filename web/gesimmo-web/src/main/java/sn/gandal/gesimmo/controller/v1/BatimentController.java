/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller.v1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.BatimentForm;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.Localisation.ETAT;
import sn.gandal.gesimmo.modele.client.entities.User;

/**
 *
 * @author DYSOW
 */
@Controller(value = "batimentV1Controller")
@RequestMapping(value = "/batiment")
public class BatimentController {

    @Autowired
    GesImmoServiceManager serviceManager;

    Logger LOG = Logger.getLogger(BatimentController.class.getName());

    /**
     * Creer un batiment depuis un site
     * @param batimentForm
     * @param request
     * @param m
     * @return 
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BasicResponse newBatimentFromParent(@RequestBody BatimentForm batimentForm, HttpServletRequest request, Model m) {
        
        BasicResponse resultat= new BasicResponse();
            String clef = batimentForm.getCleLocalite();
            String etatBatiment = batimentForm.getEtatBatiment();
            Long idLocaliteSiteParent = batimentForm.getIdLocalisation(); //idLocalisationParent
            //String rattachemntParent = batimentForm.getRattachement(); //idRattachement
            String resp = batimentForm.getResponsable();  // idResponsable
            Localisation locFind  = serviceManager.getLocalisationService().getLocalisationById(idLocaliteSiteParent); // ne peut être null
            
        /** CONTROLLER LES CHAMPS RECUS POUR S'ASSURER QU'ILS SONT VALIDES **/
        
        
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            User responsable = (User) serviceManager.getGesimmoMetierService().findEntityById(Long.parseLong(resp), User.class);
            User createur = serviceManager.getGesimmoMetierService().findUserByLogin(getCurrentUserName());
            
                BatimentLocalite localisation = new BatimentLocalite();
                localisation.setNomLocalisable(batimentForm.getNom());
                localisation.setNomLocalisableGoogle(batimentForm.getNom());
                localisation.setClef(clef);
                localisation.setDescription(batimentForm.getDescription());
                localisation.setLatitude(locFind.getLatitude());
                localisation.setLongitude(locFind.getLongitude());
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setType(batimentForm.getTypeBatiment());
                localisation.setEtat(ETAT.valueOf(batimentForm.getEtatBatiment()));
             //   localisation.setNbNiveaux(Integer.parseInt(batimentForm.getNbNiveaux()));
                localisation.setZone(locFind.getZone());
                Localisation localisationCreated = serviceManager.getLocalisationService().saveLocalisation(localisation);
               
                resultat.setResultat(batimentForm.RETOUR_OK);
                resultat.setMsg("Objet localisable crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            ex.printStackTrace();
            resultat.setResultat(batimentForm.RETOUR_EXCEPTION);
            resultat.setMsg("Une erreur est survenue pendant le traitement!! " + ex);
        } finally {
            return resultat;
        }

    }

    private String getCurrentUserName() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        LOG.log(Level.INFO, "Username recuperé : " + userName);
        return userName;
    }
}
