package sn.gandal.gesimmo.controller.v1;

import sn.gandal.gesimmo.dto.EditLocalisationDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import sn.gandal.gesimmo.form.*;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.dto.BasicResponse;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;

import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Compte;
import sn.gandal.gesimmo.modele.client.entities.Niveau;
import sn.gandal.gesimmo.modele.client.entities.Proprietaire;
import sn.gandal.gesimmo.modele.client.entities.SiteLocalite;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.entities.Zone;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

/**
 *
 * @author ddiaw
 */
@Controller(value = "localisationV1Controller")
@RequestMapping(value = "/localisation")
public class LocalisationController {

    @Autowired
    ILocalisationMetier localisationMetier;

    @Autowired
    IProjetMetier projetMetier;

    @Autowired
    IGesimmoMetier gesimmoMetier;

    @Autowired
    GesImmoServiceManager serviceManager;

    @Autowired
    IIndicateurMetier IndicateurMetier;

    private long idLocalisationSaisi = 0;
    
    private String DTypeSelectionne = null;
    
    FormPopulationHelper helper = new FormPopulationHelper();

    Logger LOG = Logger.getLogger(LocalisationController.class.getName());

    @RequestMapping(value = "/list")
    public String localisationListForm(Model model) {
        EditLocalisationForm localisationForm = new EditLocalisationForm();
        List<Localisation> elements = localisationMetier.findLocalisationByEtat(Localisation.ETAT.FONCTIONNEL);
        model.addAttribute("localisations", elements);
        if (elements.size() > 0) {
            model.addAttribute("localisationSelected", elements.get(0));
            localisationForm = helper.getCompletedLocalisationForm(elements.get(0), serviceManager);
            model.addAttribute("localisationForm", localisationForm);
            
            BatimentForm batimmentForm = new BatimentForm();
        Localisation loc =elements.get(0);
        batimmentForm.setIdLocalisation(loc.getIdLocalisation());
        batimmentForm.setLongitude(loc.getLongitude());
        batimmentForm.setLatitude(loc.getLatitude());
        batimmentForm.setZone(loc.getZone() != null ? loc.getZone().getIdZone().toString(): "");
        batimmentForm.setResponsable(loc.getAttribution()!= null ? loc.getAttribution().toString():"");
        batimmentForm.setRattachement(loc.getIdLocalisation().toString());
        batimmentForm.setRattachements(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        batimmentForm.setResponsables(gesimmoMetier.findAllResponsables());
        localisationForm.setProprietaires(gesimmoMetier.findAllProprietaires());
        batimmentForm.setListResponsables(gesimmoMetier.findAllUsers());
        batimmentForm.setZones(serviceManager.getZoneService().getAllZones());
        batimmentForm.setTypesBatiment(BatimentLocalite.getAllTypes());
        batimmentForm.setEtatsBatiment(Localisation.getAllEtats());
        
        batimmentForm.setCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesMap());
        batimmentForm.setCaracteristique(loc.getCaracteristiquesTab());       
        
        model.addAttribute("batimentForm", batimmentForm);
        
        NiveauForm niveauForm =  new NiveauForm();
        if(loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_BATIMENT)){
          BatimentLocalite batm=  (BatimentLocalite) loc;
          niveauForm.setLevels( batm.exclureNiveauxSaisis());
          niveauForm.setEtats( Niveau.getEtatCollection());
          
          
        }
        model.addAttribute("niveauForm",niveauForm);
        }else{
          model.addAttribute("localisationForm", null);  
          model.addAttribute("batimentForm", null);
          model.addAttribute("niveauForm", null);
        }
        
        
        /**
         * *********************
         */
        LocalisationFormFilter localisationFormFilter;
        localisationFormFilter = new LocalisationFormFilter();
        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        model.addAttribute("localisationFormFilter", localisationFormFilter);

        FilterLocalisationForm filterLocalisationForm = new FilterLocalisationForm();
        filterLocalisationForm.populateEtatsList(Arrays.asList(Localisation.ETAT.values()));
        filterLocalisationForm.setEtatSaisi("");
        filterLocalisationForm.setZones(serviceManager.getZoneService().getAllZones());
        filterLocalisationForm.setZoneSaisi("");
        filterLocalisationForm.setObjets(gesimmoMetier.getTypeLicenceLocalite());
        filterLocalisationForm.setObjetSaisi("");
        model.addAttribute("filterLocalisationForm", filterLocalisationForm);

        //getCurrentUserName();
        
        return "localisation/list";
    }



    //TODO ne pas utiliser l'entité au niveau de la couche web
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String getLolcalisation(@PathVariable(value = "id") Long idLocalisation, Model model) {
      // EditLocalisationForm formBinding = new EditLocalisationForm();
        //1 - peuler l'objet dto qui devra contenir toutes les informations ncessaires

        Localisation loc = localisationMetier.getLocalisationById(idLocalisation);
        
        EditLocalisationForm localisationForm = helper.getCompletedLocalisationForm(loc, serviceManager);
     
        model.addAttribute("localisationSelected", loc);
        model.addAttribute("localisationForm", localisationForm);
        
        BatimentForm batimmentForm = new BatimentForm();
        batimmentForm.setIdLocalisation(idLocalisation);
        batimmentForm.setLongitude(loc.getLongitude());
        batimmentForm.setLatitude(loc.getLatitude());
        batimmentForm.setZone(loc.getZone() != null ? loc.getZone().getIdZone().toString(): "");
        batimmentForm.setResponsable(loc.getAttribution()!= null ? loc.getAttribution().toString():"");
        batimmentForm.setRattachement(loc.getIdLocalisation().toString());
        batimmentForm.setRattachements(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        batimmentForm.setResponsables(gesimmoMetier.findAllResponsables());
        batimmentForm.setListResponsables(gesimmoMetier.findAllUsers());
        batimmentForm.setZones(serviceManager.getZoneService().getAllZones());
        batimmentForm.setTypesBatiment(BatimentLocalite.getAllTypes());
        batimmentForm.setEtatsBatiment(Localisation.getAllEtats());
        
        batimmentForm.setCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesMap());
        batimmentForm.setCaracteristique(loc.getCaracteristiquesTab());       
        
        
        model.addAttribute("batimentForm", batimmentForm);
        model.addAttribute("responsables", gesimmoMetier.findAllResponsables());
        model.addAttribute("proprietaires", gesimmoMetier.findAllProprietaires());
        
        NiveauForm niveauForm =  new NiveauForm();
            if(loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_BATIMENT)){
              BatimentLocalite batm=  (BatimentLocalite) loc;
                niveauForm.setLevels( batm.exclureNiveauxSaisis());
                niveauForm.setEtats( Niveau.getEtatCollection());
                niveauForm.setListeCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesMap());
                niveauForm.setCaracteristique(batm.getCaracteristiquesTab());
            }
        model.addAttribute("niveauForm", niveauForm);
        
        return "fragment/localisation/view";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public @ResponseBody
    EditLocalisationForm getLolcalisation(@RequestParam(value = "idLocalisation") Long idLocalisation, @RequestParam(value = "dtype") String dtype) {

        EditLocalisationForm editLolcalisation = new EditLocalisationForm();
        if (dtype.equals(TableConfig.DTYPE_LOCALITE)) {
            BatimentLocalite localisation = new BatimentLocalite();
            DTypeSelectionne = TableConfig.DTYPE_LOCALITE;
            localisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisation);
            if (localisation == null) {
                editLolcalisation.setMsg("Element introuvable.");
                editLolcalisation.setResultat(BasicResponse.RETOUR_EXCEPTION);
                return editLolcalisation;
            }

            idLocalisationSaisi = idLocalisation;

            editLolcalisation.setNom(localisation.getNomLocalisable());
            editLolcalisation.setDescription(localisation.getDescription());
            editLolcalisation.setLongitude(localisation.getLongitude());
            editLolcalisation.setLatitude(localisation.getLatitude());
            editLolcalisation.setType(localisation.getTypeToString());
            editLolcalisation.setGravite(null);
            editLolcalisation.setTypeIncidentOuLocalite(null);
            editLolcalisation.setTypeIncidentOuLocalite(BatimentLocalite.getAllTypes());
            if (localisation.getParentLocalisation() != null) {
                editLolcalisation.setRattachement(localisation.getParentLocalisation().toString());
            }
            editLolcalisation.setMsg("Visualisation de l'objet localisable passée avec succés");
            return editLolcalisation;
        }
        if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
            ObjetIncident localisation;//= new ObjetIncident();
            DTypeSelectionne = TableConfig.DTYPE_INCIDENT;
            localisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisation);
            if (localisation == null) {
                editLolcalisation.setMsg("Element introuvable.");
                editLolcalisation.setResultat(BasicResponse.RETOUR_EXCEPTION);
                return editLolcalisation;
            }

            idLocalisationSaisi = idLocalisation;

            editLolcalisation.setNom(localisation.getNomLocalisable());
            editLolcalisation.setDescription(localisation.getDescription());
            editLolcalisation.setLongitude(localisation.getLongitude());
            editLolcalisation.setLatitude(localisation.getLatitude());
            editLolcalisation.setType(localisation.getTypeToString());
            editLolcalisation.setGravite(localisation.getGravite());
            editLolcalisation.setTypeIncidentOuLocalite(null);
            editLolcalisation.setTypeIncidentOuLocalite(ObjetIncident.getAllTypes());
            if (localisation.getParentLocalisation() != null) {
                editLolcalisation.setRattachement(localisation.getParentLocalisation().toString());
            }
            editLolcalisation.setMsg("Visualisation de l'objet localisable passée avec succés");
            return editLolcalisation;
        }
        editLolcalisation.setMsg("TYPE INDISPONIBLE");
        return editLolcalisation;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    EditLocalisationDTO editLolcalisation(@RequestBody @Valid EditLocalisationDTO editLocaliteForm, HttpServletRequest request) {

        Long idLocalisation = editLocaliteForm.getIdLocalisation();

        if (editLocaliteForm.getdT().equalsIgnoreCase(TableConfig.DTYPE_SITE)) {
            SiteLocalite currentLocalisation = (SiteLocalite) localisationMetier.getLocalisationById(idLocalisation);
            setDefaultParams(currentLocalisation, editLocaliteForm);
           // currentLocalisation.setNbObjets(Integer.valueOf(editLocaliteForm.getNbObjets()));
            localisationMetier.updateLocalisation(currentLocalisation);
        } else if (editLocaliteForm.getdT().equalsIgnoreCase(TableConfig.DTYPE_BATIMENT)) {
            BatimentLocalite currentLocalisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisation);
            setDefaultParams(currentLocalisation, editLocaliteForm);
            //currentLocalisation.setNbNiveaux(Integer.valueOf(editLocaliteForm.getNbNiveaux()));
            localisationMetier.updateLocalisation(currentLocalisation);
        } else if (editLocaliteForm.getdT().equalsIgnoreCase(TableConfig.DTYPE_INCIDENT)) {
            ObjetIncident currentLocalisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisation);
            setDefaultParams(currentLocalisation, editLocaliteForm);
            currentLocalisation.setGravite(editLocaliteForm.getGravite());
            localisationMetier.updateLocalisation(currentLocalisation);
        }

        editLocaliteForm.setMsg("Objet modifié avec succès !");
        editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
        return editLocaliteForm;

    }
    
     @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditLocalisationForm addLocalisation(@RequestBody EditLocalisationForm localisationForm, HttpServletRequest request, Model m) {
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            String dtype = localisationForm.getdT();
            String clef = localisationForm.getCleLocalite();
            String[] gps = localisationForm.getCoordonnees().split("\\(");
            gps = gps[1].split("\\)");
            gps = gps[0].split(",");
            System.out.println("GPS Data Splitted : 0 : " + gps[0] + " 1 :" + gps[1]);
            String latitude = gps[0];
            String longitude = gps[1];
            String Ratt = localisationForm.getRattachement();
            String resp = localisationForm.getResponsable();
            String proprio = localisationForm.getProprietaire();
            String zone = localisationForm.getZone();

            String userName = getCurrentUserName();
            LOG.log(Level.INFO, "Username recuperé : " + userName);
            Localisation locFind = null;
            if (!Ratt.equals("")) {
                Long idRatt = Long.parseLong(Ratt);
                locFind = localisationMetier.getLocalisationById(idRatt);
            }
            User responsable = null;
            if (resp != null && !resp.equals("")) {
                try {
                    Long idResponsable = Long.parseLong(resp);
                    responsable = (User) gesimmoMetier.findEntityById(idResponsable, User.class);
                } catch (Exception ex) {
                    LOG.severe("On a tenté d'ajouter un responsable avec un ID inconnu : ID : " + resp);
                }

            }
            Zone zoneObjet = null;
            if (zone != null && !zone.equals("")) {
                try {
                    zoneObjet = (Zone) gesimmoMetier.findEntityById(Long.parseLong(zone), Zone.class);
                } catch (Exception ex) {
                    LOG.severe("On a tenté d'ajouter une Zone avec un ID inconnu : ID : " + zone);
                }
            }

            User createur = null;
            Compte cpt = gesimmoMetier.findAccountByLogin(userName);

            if (cpt != null && cpt.getUser() != null) {
                createur = cpt.getUser();

            }
            
            Proprietaire proprietaire = null;
            if (proprio != null && !proprio.equals("")) {
                try {
                    Long idProprio = Long.parseLong(proprio);
                    proprietaire = (Proprietaire) gesimmoMetier.findEntityById(idProprio, Proprietaire.class);
                } catch (Exception ex) {
                    LOG.severe("On a tenté d'ajouter un proprietaire de ben immobilier avec un ID inconnu : ID : " + proprio);
                }

            }

            if (dtype.equals(TableConfig.DTYPE_BATIMENT)) {
                BatimentLocalite localisation = new BatimentLocalite();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setClef(clef);
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setProprietaire(proprietaire);
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);
               // localisation.setNbNiveaux(Integer.parseInt(localisationForm.getNbNiveaux()));
                localisation.setZone(zoneObjet);
                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");
                m.addAttribute("localisationForm", localisationForm);
            } else if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
                ObjetIncident localisation = new ObjetIncident();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setClef(clef);
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setProprietaire(proprietaire);
                localisation.setGravite(localisationForm.getGravite());
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);
                localisation.setZone(zoneObjet);
                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");
                m.addAttribute("localisationForm", localisationForm);
            } else if (dtype.equals(TableConfig.DTYPE_SITE)) {
                SiteLocalite localisation = new SiteLocalite();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setClef(clef);
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setProprietaire(proprietaire);
                //localisation.setNbObjets(Integer.parseInt(localisationForm.getNbObjets()));
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);
                localisation.setZone(zoneObjet);
                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable Site crée avec succès avec les informations suivantes : [ clef =  " + clef + " ]");
                m.addAttribute("localisationForm", localisationForm);
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            ex.printStackTrace();
            localisationForm.setResultat(localisationForm.RETOUR_EXCEPTION);
            localisationForm.setMsg("Une erreur est survenue pendant le traitement!! " + ex);
        } finally {
            return localisationForm;
        }

    }

    /**
     * Appelé par googleMap pour remplir la carte
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/charger", method = RequestMethod.GET)
    public @ResponseBody
    String[][] getLocaliteByType(@RequestParam(value = "type") String type) {

        String[][] locations = null;
        if (type == null || type.equalsIgnoreCase("ALL")) {
            return localisationMetier.findAllLocalisationByEtatArray(Localisation.ETAT.FONCTIONNEL);
        } else {
            //return localisationMetier.findAllTLocaliteByTypeAndEtatArray(BatimentLocalite.TYPE.valueOf(type), 1);
            return localisationMetier.findAllTLocalisationByTypeAndEtatArray(type, Localisation.ETAT.FONCTIONNEL);
        }

    }

    @RequestMapping(value = "/load/localite", method = RequestMethod.GET)
    public String getDefaultLocaliteForm(Model model, @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "add") String add) {

        /*
         *
         * VERIFIER QUE LES DONNEES RECUES SONT AU BON FORMAT
         * */
        EditLocalisationForm localisationForm = new EditLocalisationForm();
        localisationForm.setLatitude(lat);
        localisationForm.setLongitude(lon);
        localisationForm.setCoordonnees("(" + lat + "," + lon + ")");
        localisationForm.setNomLocaliteGoogle(add);
        localisationForm.setNom(add);
        List<Localisation> locs = new ArrayList<Localisation>();
        locs.addAll(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        localisationForm.setRattachements(locs);
        localisationForm.setResponsables(gesimmoMetier.findAllResponsables());
        localisationForm.setProprietaires(gesimmoMetier.findAllProprietaires());
        localisationForm.setZones(serviceManager.getZoneService().getAllZones());

        List<String> licencesObjetType = gesimmoMetier.getTypeLicenceLocalite();
        //il faut verifier les dtype de la licence
        // TODO A GERER DANS UN OBJECT LicenceContent plus global à la gestion des licences dans une seule methode metier
        if (licencesObjetType != null && licencesObjetType.size() > 0) {
            localisationForm.setResultat(BasicResponse.RETOUR_GOOD_LICENCE);
            localisationForm.setMsg("Success");
            localisationForm.setdT(licencesObjetType.get(0));
            localisationForm.setTypeIncidentOuLocalite(getTypeOfDtypeElements(licencesObjetType.get(0)));
        } else {
            // RENOYER UNE BasicResponse avec code specifique
            localisationForm.setResultat(BasicResponse.RETOUR_DAB_LICENCE);
            localisationForm.setMsg("<p> Votre licence ne vous permet pas d'effectuer cette operation</p><br>");
        }

        boolean isPerimetreOk = gesimmoMetier.isZoneInLicenceArea(Double.parseDouble(lat), Double.parseDouble(lon));

        if (!isPerimetreOk) {
            // RENOYER UNE BasicResponse avec code specifique pour dire qu'il est dehors du perimetre de la licence
            localisationForm.setResultat(BasicResponse.RETOUR_ZONE_NOT_IN_LICENCE);
            localisationForm.setMsg("<p>" + localisationForm.getMsg() + " Vous etes en dehors du perimetre de la licence</p><br>");
        }
        // verifier si lon et lat sont dans le perimetre
        localisationForm.setdType(licencesObjetType);

        model.addAttribute("localisationForm", localisationForm);

        return "fragment/localisation/new";

    }

    @RequestMapping(value = "/load/localite/type", method = RequestMethod.GET)
    public String getLocaliteFormByType(Model model, @RequestParam(value = "type") String type,
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "add") String add) {

        EditLocalisationForm localisationForm = new EditLocalisationForm();
        localisationForm.setLatitude(lat);
        localisationForm.setLongitude(lon);
        localisationForm.setCoordonnees("(" + lat + "," + lon + ")");
        localisationForm.setNomLocaliteGoogle(add);
        localisationForm.setNom(add);
        localisationForm.setResultat(BasicResponse.RETOUR_GOOD_LICENCE);
        localisationForm.setMsg("Success");
        List<Localisation> locs = new ArrayList<Localisation>();
        List<String> licencesObjetType = gesimmoMetier.getTypeLicenceLocalite();
        locs.addAll(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        localisationForm.setRattachements(locs);
        localisationForm.setResponsables(gesimmoMetier.findAllResponsables());
        localisationForm.setProprietaires(gesimmoMetier.findAllProprietaires());
        localisationForm.setZones(serviceManager.getZoneService().getAllZones());
        localisationForm.setTypeIncidentOuLocalite(getTypeOfDtypeElements(type));
        localisationForm.setdType(licencesObjetType);
        localisationForm.setdT(type);
        model.addAttribute("localisationForm", localisationForm);

        return "fragment/localisation/new";

    }

        @RequestMapping(value = "/filtrer", method = RequestMethod.GET)
    public String getLocalisationListFiltered(Model model,
            @RequestParam(value = "objetSaisi") String dtype,
            @RequestParam(value = "zoneSaisi") String zone,
            @RequestParam(value = "etatSaisi") String etat
    ) {

        List<Localisation> allLocalites = localisationMetier.findAllLocalisations();
        if (dtype != null && !dtype.equalsIgnoreCase("ALL")) {
            allLocalites = filterLocalite(allLocalites, "DTYPE", dtype);
        }
        if (zone != null && !zone.equalsIgnoreCase("ALL")) {
            allLocalites = filterLocalite(allLocalites, "ZONE", zone);
        }
        if (etat != null && !etat.equalsIgnoreCase("ALL")) {
            allLocalites = filterLocalite(allLocalites, "ETAT", etat);
        }

        model.addAttribute("localisations", allLocalites);
        if (allLocalites.size() > 0) {
            model.addAttribute("localisationSelected", allLocalites.get(0));
            EditLocalisationForm localisationForm = helper.getCompletedLocalisationForm(allLocalites.get(0), serviceManager);
                        
            BatimentForm batimmentForm = new BatimentForm();
            Localisation loc =allLocalites.get(0);
            batimmentForm.setIdLocalisation(loc.getIdLocalisation());
            batimmentForm.setLongitude(loc.getLongitude());
            batimmentForm.setLatitude(loc.getLatitude());
            batimmentForm.setZone(loc.getZone() != null ? loc.getZone().getIdZone().toString(): "");
            batimmentForm.setRattachement(loc.getIdLocalisation().toString());
            batimmentForm.setRattachements(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
            batimmentForm.setResponsable(loc.getAttribution()!= null ? loc.getAttribution().toString():"");
            batimmentForm.setResponsables(gesimmoMetier.findAllResponsables());
            batimmentForm.setListResponsables(gesimmoMetier.findAllUsers());
            batimmentForm.setZones(serviceManager.getZoneService().getAllZones());
            batimmentForm.setTypesBatiment(BatimentLocalite.getAllTypes());
            batimmentForm.setEtatsBatiment(Localisation.getAllEtats()); 
                batimmentForm.setCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesMap());
                batimmentForm.setCaracteristique(loc.getCaracteristiquesTab());
            model.addAttribute("localisationForm", localisationForm); 
            model.addAttribute("batimentForm", batimmentForm);
            
            NiveauForm niveauForm =  new NiveauForm();
            if(loc.getDType().equalsIgnoreCase(TableConfig.DTYPE_BATIMENT)){
                 BatimentLocalite batm=  (BatimentLocalite) loc;
                 niveauForm.setLevels( batm.exclureNiveauxSaisis());
                 niveauForm.setEtats( Niveau.getEtatCollection());
                 niveauForm.setListeCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesMap());
                 niveauForm.setCaracteristique(batm.getCaracteristiquesTab());
            }
            model.addAttribute("niveauForm", niveauForm);
        } else {
            model.addAttribute("localisationForm", null);
            model.addAttribute("localisationSelected", null);
            model.addAttribute("batimentForm", null);
            model.addAttribute("niveauForm", null);
        }

        /**
         * *********************
         */
        FilterLocalisationForm filterLocalisationForm;
        filterLocalisationForm = new FilterLocalisationForm();
        filterLocalisationForm.populateEtatsList(Arrays.asList(Localisation.ETAT.values()));
        filterLocalisationForm.setEtatSaisi(etat);
        filterLocalisationForm.setZones(serviceManager.getZoneService().getAllZones());
        filterLocalisationForm.setZoneSaisi(zone);
        filterLocalisationForm.setObjets(gesimmoMetier.getTypeLicenceLocalite());
        filterLocalisationForm.setObjetSaisi(dtype);
        model.addAttribute("filterLocalisationForm", filterLocalisationForm);

        //getCurrentUserName();
        
        return "localisation/list";
    }

    private List<String> getTypeOfDtypeElements(String categorie) {
        if (categorie.equals(TableConfig.DTYPE_BATIMENT)) {
            return BatimentLocalite.getAllTypes();
        } else if (categorie.equals(TableConfig.DTYPE_INCIDENT)) {
            return ObjetIncident.getAllTypes();
        } else if (categorie.equals(TableConfig.DTYPE_SITE)) {
            return SiteLocalite.getAllTypes();
        } else {
            return new ArrayList<String>();
        }

    }

    @RequestMapping(value = "/verify/key/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    BasicResponse verifyIfKeyExist(@PathVariable(value = "id") String key, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setMsg("Clef existe dejà");
        response.setResultat(BasicResponse.RETOUR_ID_INVALID);
        if (!localisationMetier.isKeyExist(key)) {
            response.setResultat(BasicResponse.RETOUR_OK);
            response.setMsg("Clef VALIDE");
        }
        return response;

    }

    private String getCurrentUserName() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        LOG.log(Level.INFO, "Username recuperé : " + userName);
        return userName;
    }

    private void setDefaultParams(Localisation currentLocalisation, EditLocalisationDTO editLocaliteForm) {
        currentLocalisation.setNomLocalisable(editLocaliteForm.getNom());
        String nom = editLocaliteForm.getNom();
        String desc = editLocaliteForm.getDescription();
        currentLocalisation.setDescription(editLocaliteForm.getDescription());
        currentLocalisation.setType(editLocaliteForm.getType());
        currentLocalisation.setCaracteristiques(serviceManager.getLocalisationService().findCaracteristiquesFromList(editLocaliteForm.getCaracteristique()));
        String type = editLocaliteForm.getType();
        String gravite = editLocaliteForm.getGravite();
        //currentLocalisation.set
        Long oldRattachementId = editLocaliteForm.getOldRattachement();
        Long rattachementId = editLocaliteForm.getRattachement();

        Long oldResponsableId = editLocaliteForm.getOldResponsable();
        Long responsableId = editLocaliteForm.getResponsable();
        Long proprietaireId = editLocaliteForm.getProprietaire();
        Long oldProprietaireId = editLocaliteForm.getOldProprietaire();

        Long oldZoneId = editLocaliteForm.getOldZone();
        Long zoneId = editLocaliteForm.getZone();

        User attribution = null;
        Localisation rattachement = null;
        Proprietaire proprietaire = null;
        Zone zone = null;

        if (rattachementId != null) {
            if (oldRattachementId != rattachementId) {
                rattachement = localisationMetier.getLocalisationById(Long.valueOf(rattachementId));
                currentLocalisation.setParentLocalisation(rattachement);
            }
        }else if(rattachementId == null){
            //cas changement ==> suppression du rattachement
            currentLocalisation.setParentLocalisation(null);
        }else{
            LOG.log(Level.WARNING, "Aucun test ne correspond sur le rattachement de l'objet localisable à son parent");
        }
        
        //TODO applique les conditions en cas de modification à null pour les autres attributs
        // dans le cas d'une suppression du rattachement
        if(rattachementId != null ){
            if(oldRattachementId != null ){
                currentLocalisation.setParentLocalisation(null);
            }
        }

        if (responsableId != null) {
            if (oldResponsableId != responsableId) {
                attribution = gesimmoMetier.findUserById(Long.valueOf(responsableId));
                currentLocalisation.setAttribution(attribution);
            } 
        }else {               
           currentLocalisation.setAttribution(null);
       }

        if (zoneId != null) {
            if ( oldZoneId != zoneId) {
                zone = (Zone) gesimmoMetier.findEntityById(Long.valueOf(zoneId), Zone.class);
                currentLocalisation.setZone(zone);
            }
        }else{
            currentLocalisation.setZone(null);
        }
        
         if (proprietaireId != null ) { 
                if ((oldProprietaireId != proprietaireId) ) { 
                    proprietaire = (Proprietaire) gesimmoMetier.findEntityById(Long.valueOf(proprietaireId), Proprietaire.class);                
                    currentLocalisation.setProprietaire(proprietaire);
                }
        } else{ 
             currentLocalisation.setProprietaire(null);             
         }
    }

    private List<Localisation> filterLocalite(List<Localisation> allLocalites, String type, String value) {
        List<Localisation> resultat = new ArrayList<Localisation>();
        if (type != null && type.equalsIgnoreCase("DTYPE")) {
            for (Localisation loc : allLocalites) {
                if (loc.getDType().equalsIgnoreCase(value)) {
                    resultat.add(loc);
                }
            }
            return resultat;
        }
        if (type != null && type.equalsIgnoreCase("ZONE")) {
            for (Localisation loc : allLocalites) {
                if (loc.getZone() != null && loc.getZone().getIdZone().toString().equalsIgnoreCase(value)) {
                    resultat.add(loc);
                }
            }
            return resultat;
        }
        if (type != null && type.equalsIgnoreCase("ETAT")) {
            for (Localisation loc : allLocalites) {
                if (loc.getEtat().name().equalsIgnoreCase(value)) {
                    resultat.add(loc);
                }
            }
            return resultat;
        }
        return allLocalites;
    }
    
    //    @RequestMapping(value = "localisationListFilter/load/localite/categorie", method = RequestMethod.GET)
//    public String getLocaliteFormBy(Model model, @RequestParam(value = "categorie") String categorie) {
//        LocalisationFormFilter localisationFormFilter = new LocalisationFormFilter();
//        localisationFormFilter.setdT(categorie);
//        LOG.info("***************************categorie:" + categorie + " ****************************");
//        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
//        localisationFormFilter.setTypeIncidentOuLocalite(getTypeOfDtypeElements(categorie));
//        localisationFormFilter.getTypeIncidentOuLocalite().add("ALL");
//        model.addAttribute("localisationFormFilter", localisationFormFilter);
//
//        return "fragment/menu_localisation";
//
//    }

//    @RequestMapping(value = "/affecterResponsable", method = RequestMethod.POST)
//    public @ResponseBody
//    EditUserForm affecterResponsableLolcalisation(@RequestBody @Valid EditUserForm editUserForm, HttpServletRequest request) {
//
//        User responsable = gesimmoMetier.findUserById(editUserForm.getIdUser());
//        Localisation localisation = localisationMetier.getLocalisationById(idLocalisationSaisi);
//        localisation.setUser(responsable);
//        localisationMetier.updateLocalisation(localisation);
//
//        editUserForm.setMsg("Modification passée  avec succés");
//        editUserForm.setResultat(Subscriber.RETOUR_OK);
//        return editUserForm;
//
//    }
//
//    @RequestMapping(value = "/ficheLocalisation", method = RequestMethod.GET)
//    String showFicheUser(Model model, @RequestParam(value = "idLocalisation") Long idLocalisation, @RequestParam(value = "dtype") String dtype) {
//        try {
//
//            if (dtype.equals(TableConfig.DTYPE_LOCALITE)) {
//                BatimentLocalite localisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisation);
//                idLocalisationSaisi = idLocalisation;
//                EditLocalisationForm editLocalisationForm = new EditLocalisationForm();
//                editLocalisationForm.setIdLocalisation(idLocalisation);
//                editLocalisationForm.setDateCreation(localisation.getDateCreation().toString());
//                editLocalisationForm.setDateUpdated(localisation.getDateUpdated().toString());
//                editLocalisationForm.setDescription(localisation.getDescription());
//                editLocalisationForm.setLatitude(localisation.getLatitude());
//                editLocalisationForm.setLongitude(localisation.getLongitude());
//                editLocalisationForm.setNom(localisation.getNomLocalisable());
//                editLocalisationForm.setType(localisation.getTypeToString());
//                List<Indicateur> mesinIndicateurs = new ArrayList<Indicateur>();
//                List<LocalisationIndicateur> lis = IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation);
//                for (LocalisationIndicateur loin : lis) {
//                    mesinIndicateurs.add(loin.getIndicateur());
//                }
//                List<Indicateur> inds = IndicateurMetier.findAllIndicateurByEtat(ParamEntity.ETAT_ACTIVE);
//                List<Indicateur> otherInds = IndicateurMetier.supprimerIndicateurDansList(inds, mesinIndicateurs);
//                //editLocalisationForm.setIdResponsable(localisation.getUser().getIdUser());
//                model.addAttribute("indicateurs", otherInds);
//
//                model.addAttribute("editLocalisationForm", editLocalisationForm);
//                /*-----------------------Responsable----------------------------*/
//                User responsable = localisation.getUser();
//                model.addAttribute("users", gesimmoMetier.findUSersByEtat(ParamEntity.ETAT_ACTIVE));
//                if (responsable == null) {
//                    model.addAttribute("editUserForm", new EditUserForm());
//                } else {
//                    EditUserForm editUserForm = new EditUserForm();
//                    editUserForm.setIdUser(responsable.getIdUser());
//                    editUserForm.setDateCreation(responsable.getDateCreation().toString());
//                    editUserForm.setDateUpdated(responsable.getDateUpdated().toString());
//                    editUserForm.setNom(responsable.getUserName());
//                    editUserForm.setPrenom(responsable.getUserPrenom());
//                    editUserForm.setEmail(responsable.getUserMail());
//                    editUserForm.setLogin(responsable.getCompte().getLogin());
//                    editUserForm.setTelephone(responsable.getUserPhone());
//                    model.addAttribute("editUserForm", editUserForm);
//
//                }
//                /*-----------------------Projets de la localite----------------------------*/
//                List<Activite> projets = new ArrayList<Activite>();
//                for (Activite activite : localisation.getActivites()) {
//                    if (activite.getType().equalsIgnoreCase(ParamEntity.ACTIVITE_TYPE_PROJET) && activite.getEtat() == ParamEntity.ETAT_ACTIVE) {
//                        projets.add(activite);
//                    }
//                }
//                ProjetForm projetForm = new ProjetForm();
//                projetForm.setNomActivite("nomActivite");
//                model.addAttribute("projetForm", projetForm);
//                model.addAttribute("projets", projets);
//                /*-----------------------l'ensemble des projets----------------------------*/
//                model.addAttribute("projetList", projetMetier.getAllProjets());
//
//                /*-----------------------Indicateur de la localite----------------------------*/
//                model.addAttribute("indicateursLocalisation", IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation));
//                LOG.info("++++++++++++++++++++++++++++" + IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation).size());
//            }
//
//            if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
//                ObjetIncident localisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisation);
//                idLocalisationSaisi = idLocalisation;
//                EditLocalisationForm editLocalisationForm = new EditLocalisationForm();
//                editLocalisationForm.setIdLocalisation(idLocalisation);
//                editLocalisationForm.setDateCreation(localisation.getDateCreation().toString());
//                editLocalisationForm.setDateUpdated(localisation.getDateUpdated().toString());
//                editLocalisationForm.setDescription(localisation.getDescription());
//                editLocalisationForm.setLatitude(localisation.getLatitude());
//                editLocalisationForm.setLongitude(localisation.getLongitude());
//                editLocalisationForm.setNom(localisation.getNomLocalisable());
//                editLocalisationForm.setType(localisation.getTypeToString());
//                List<Indicateur> mesinIndicateurs = new ArrayList<Indicateur>();
//                List<LocalisationIndicateur> lis = IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation);
//                for (LocalisationIndicateur loin : lis) {
//                    mesinIndicateurs.add(loin.getIndicateur());
//                }
//                List<Indicateur> inds = IndicateurMetier.findAllIndicateurByEtat(ParamEntity.ETAT_ACTIVE);
//                List<Indicateur> otherInds = IndicateurMetier.supprimerIndicateurDansList(inds, mesinIndicateurs);
//                //editLocalisationForm.setIdResponsable(localisation.getUser().getIdUser());
//                model.addAttribute("indicateurs", otherInds);
//
//                model.addAttribute("editLocalisationForm", editLocalisationForm);
//                /*-----------------------Responsable----------------------------*/
//                User responsable = localisation.getUser();
//                model.addAttribute("users", gesimmoMetier.findUSersByEtat(ParamEntity.ETAT_ACTIVE));
//                if (responsable == null) {
//                    model.addAttribute("editUserForm", new EditUserForm());
//                } else {
//                    EditUserForm editUserForm = new EditUserForm();
//                    editUserForm.setIdUser(responsable.getIdUser());
//                    editUserForm.setDateCreation(responsable.getDateCreation().toString());
//                    editUserForm.setDateUpdated(responsable.getDateUpdated().toString());
//                    editUserForm.setNom(responsable.getUserName());
//                    editUserForm.setPrenom(responsable.getUserPrenom());
//                    editUserForm.setEmail(responsable.getUserMail());
//                    editUserForm.setLogin(responsable.getCompte().getLogin());
//                    editUserForm.setTelephone(responsable.getUserPhone());
//                    model.addAttribute("editUserForm", editUserForm);
//
//                }
//                /*-----------------------Projets de la localite----------------------------*/
//                List<Activite> projets = new ArrayList<Activite>();
//                for (Activite activite : localisation.getActivites()) {
//                    if (activite.getType().equalsIgnoreCase(ParamEntity.ACTIVITE_TYPE_PROJET) && activite.getEtat() == ParamEntity.ETAT_ACTIVE) {
//                        projets.add(activite);
//                    }
//                }
//                ProjetForm projetForm = new ProjetForm();
//                projetForm.setNomActivite("nomActivite");
//                model.addAttribute("projetForm", projetForm);
//                model.addAttribute("projets", projets);
//                /*-----------------------l'ensemble des projets----------------------------*/
//                model.addAttribute("projetList", projetMetier.getAllProjets());
//
//                model.addAttribute("localisationForm", editLocalisationForm);
//                /*-----------------------Indicateur de la localite----------------------------*/
//                model.addAttribute("indicateursLocalisation", IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation));
//                LOG.info("++++++++++++++++++++++++++++" + IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation).size());
//            }
//
//        } catch (Exception e) {
//            System.out.println("Exception : " + e.getMessage());
//        } finally {
//            return "ficheLocalisation";
//        }
//
//    }

   
}
