/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.dto;

import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.SiteLocalite;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;

/**
 *
 * @author DYSOW
 */
public class DtoConverterUtils {
    
    

    public DtoConverterUtils() {
        
    }
    
//    public static EditLocalisationForm convert(Localisation loc){
//        
//        EditLocalisationForm result = new EditLocalisationForm(); 
//        result.setLatitude(loc.getLatitude());
//        result.setLongitude(loc.getLongitude());
//        result.setDescription(loc.getDescription());
//        result.setIdLocalisation(loc.getIdLocalisation());
//        result.setType(loc.getType());
//        result.setTypeIncidentOuLocalite(loc.getSpecificTypes());
//        result.setNom(loc.getNomLocalisable());
//        result.setResponsable(loc.getAttribution() != null ? loc.getAttribution().toString() : "");
//        result.setResponsableAttribution(loc.getAttribution());
//        result.setOldResponsable(loc.getAttribution() != null ? String.valueOf(loc.getAttribution().getIdUser()) : "");
//        
//        result.setOldRattachement(loc.getParentLocalisation() != null ? String.valueOf(loc.getParentLocalisation().getIdLocalisation()) : "");
//        result.setRattachement(loc.getParentLocalisation() != null ? loc.getParentLocalisation().toString(): "");
//
//        
//        if(loc.getDType().equals(TableConfig.DTYPE_INCIDENT)){
//            result.setGravites(ObjetIncident.getAllGraviteLevel());
//            result.setGravite(((ObjetIncident)loc).getGravite());
//        }else if(loc.getDType().equals(TableConfig.DTYPE_BATIMENT)){    
//            Integer nbNiveaux = ((BatimentLocalite)loc).getNbNiveaux();
//             result.setNbNiveaux(String.valueOf(nbNiveaux != null ? nbNiveaux : 0));
//             Integer niveauxEffectifs = ((BatimentLocalite)loc).getNiveauxSaisis().size();
//             result.setNbNiveauxEffectifs(nbNiveaux != null ? niveauxEffectifs.toString() : "0");
//        }if(loc.getDType().equals(TableConfig.DTYPE_SITE)){       
//            Integer nbObjets = ((SiteLocalite)loc).getNbObjets();
//             result.setNbObjets(String.valueOf(nbObjets != null ? nbObjets : 0));
//             
//        }
//        
//        // ajouter la zone
//        result.setZone(loc.getZone() != null ? loc.getZone().getIdZone().toString() : "");
//        result.setOldZone(loc.getZone()!= null ? String.valueOf(loc.getZone().getIdZone()) : "");
//        result.setdT(loc.getDType());
//        result.setCleLocalite(loc.getKeyPath());
//        return result;
//    }
    
    
    
}
