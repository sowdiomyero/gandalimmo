/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IZoneMetier;
import sn.gandal.gesimmo.modele.client.entities.Zone;
import sn.gandal.gesimmo.service.IZoneDao;

/**
 *
 * @author DYSOW
 */
@Service
public class ZoneMetierImpl implements IZoneMetier{

     @Autowired
     IZoneDao dao;
     
    @Override
    public List<Zone> getAllZones() {
        return dao.findAllZone();
    }
    
}
