/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.UserActivite;
import sn.gandal.gesimmo.modele.client.entities.Zone;

/**
 *
 * @author msall
 */
public interface IZoneMetier {
   
    public List<Zone> getAllZones();
}
