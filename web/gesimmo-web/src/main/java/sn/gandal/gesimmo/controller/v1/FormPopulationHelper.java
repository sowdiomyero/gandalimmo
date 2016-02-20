/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.controller.v1;


import java.util.List;
import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;

/**
 *
 * @author DYSOW
 */
public class FormPopulationHelper {

    
    public EditLocalisationForm getCompletedLocalisationForm(Localisation loc, GesImmoServiceManager serviceManager){
        
        EditLocalisationForm result = new EditLocalisationForm(); 
        result.setLatitude(loc.getLatitude());
        result.setLongitude(loc.getLongitude());
        result.setDescription(loc.getDescription());
        result.setIdLocalisation(loc.getIdLocalisation());
        result.setType(loc.getType());
        result.setTypeIncidentOuLocalite(loc.getSpecificTypes());
        result.setNom(loc.getNomLocalisable());
        result.setResponsable(loc.getAttribution() != null ? loc.getAttribution().toString() : "");
        result.setResponsableAttribution(loc.getAttribution());
        result.setOldResponsable(loc.getAttribution() != null ? String.valueOf(loc.getAttribution().getIdUser()) : "");       
        result.setOldRattachement(loc.getParentLocalisation() != null ? String.valueOf(loc.getParentLocalisation().getIdLocalisation()) : "");
        result.setRattachement(loc.getParentLocalisation() != null ? loc.getParentLocalisation().toString(): "");
        
        if(loc.getDType().equals(TableConfig.DTYPE_INCIDENT)){
            result.setGravites(ObjetIncident.getAllGraviteLevel());
            result.setGravite(((ObjetIncident)loc).getGravite());
            result.setRattachements(serviceManager.getLocalisationService().findAllLocalisationByDType(TableConfig.DTYPE_INCIDENT)); 
        }else if(loc.getDType().equals(TableConfig.DTYPE_BATIMENT)){    
             result.setRattachements(serviceManager.getLocalisationService().findAllLocalisationByDType(TableConfig.DTYPE_SITE)); 
             Integer niveauxEffectifs = ((BatimentLocalite)loc).getNiveauxSaisis().size();
             result.setNbNiveauxEffectifs(niveauxEffectifs != null ? niveauxEffectifs.toString() : "0");
        }if(loc.getDType().equals(TableConfig.DTYPE_SITE)){       
             result.setRattachements(serviceManager.getLocalisationService().findAllLocalisationByDType(TableConfig.DTYPE_SITE)); 
             List objetsEffectifs = loc.getLocalisationChilds() ;//serviceManager.getLocalisationService().findLocalisationChild(loc.getIdLocalisation());
             Integer nbEffectifs = objetsEffectifs !=null ? objetsEffectifs.size() : 0;
             result.setNbNiveauxEffectifs(nbEffectifs.toString());
        }
        
        result.getRattachements().remove(loc);
        // ajouter la zone
        result.setZone(loc.getZone() != null ? loc.getZone().getIdZone().toString() : "");
        result.setOldZone(loc.getZone()!= null ? String.valueOf(loc.getZone().getIdZone()) : "");
        result.setdT(loc.getDType());
        result.setCleLocalite(loc.getKeyPath());
               
        result.setResponsables(serviceManager.getGesimmoMetierService().findAllResponsables());
        result.setListResponsables(serviceManager.getGesimmoMetierService().findAllUsers());

        result.setdType(serviceManager.getGesimmoMetierService().getTypeLicenceLocalite());
        result.setZones(serviceManager.getZoneService().getAllZones());         
       
        return result;
    }
}
