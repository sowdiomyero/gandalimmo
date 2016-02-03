/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.controller.v1;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;
import sn.gandal.gesimmo.modele.client.entities.Localisation;

/**
 *
 * @author DYSOW
 */
@Controller(value = "localisationBrowser")
@RequestMapping(value = "/browse")
public class LocalisationBrowser {
  
    Logger LOG = Logger.getLogger(LocalisationBrowser.class.getName());
    
    @Autowired
    GesImmoServiceManager manager;
    
    @RequestMapping(value = "/site/{key}", method = RequestMethod.GET)
    public String localisationListForm(@PathVariable(value = "key")String clef, Model model) {
    if(clef != null && clef.split("-").length>=2){
        String[] composedKey = clef.split("-");
        String localisationKey = composedKey[0];
        Long localisationId = Long.parseLong(composedKey[1]);
        
        Localisation loc = manager.getLocalisationService().getLocalisationById(localisationId);
        
    }else{
        
    }
    return "accueil";
    }
}
