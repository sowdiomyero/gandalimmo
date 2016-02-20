/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sn.gandal.gesimmo.modele.client.entities.Localisation.ETAT;
import sn.gandal.gesimmo.modele.client.entities.Zone;

/**
 *
 * @author DYSOW
 */
public class FilterLocalisationForm {
   
    String zoneSaisi;
    String etatSaisi;
    String objetSaisi;
    
    List<String> etats = new ArrayList<String>();
    List<String> objets = new ArrayList<String>();
    
    Map<Long,String> zones = new HashMap<Long,String>();

    public FilterLocalisationForm() {
    }

    public String getZoneSaisi() {
        return zoneSaisi;
    }

    public void setZoneSaisi(String zoneSaisi) {
        this.zoneSaisi = zoneSaisi;
    }

    public String getEtatSaisi() {
        return etatSaisi;
    }

    public void setEtatSaisi(String etatSaisi) {
        this.etatSaisi = etatSaisi;
    }

    public String getObjetSaisi() {
        return objetSaisi;
    }

    public void setObjetSaisi(String objetSaisi) {
        this.objetSaisi = objetSaisi;
    }

    public List<String> getEtats() {
        return etats;
    }

    public void setEtats(List<String> etats) {
        this.etats = etats;
    }

    public void populateEtatsList(List<ETAT> ets) {
        for(ETAT e : ets){
            this.etats.add(e.name());
        }
    }

    public List<String> getObjets() {
        return objets;
    }

    public void setObjets(List<String> objets) {
        this.objets = objets;
    }

    public Map<Long, String> getZones() {
        return zones;
    }

    public void setZones(Map<Long, String> zones) {
        this.zones = zones;
    }
    
    public void setZones(List<Zone> zns) {
       for(Zone zo : zns){
           this.zones.put(zo.getIdZone(), zo.getNomZoneGoogle());
       } 
    }
    
    
    
}
