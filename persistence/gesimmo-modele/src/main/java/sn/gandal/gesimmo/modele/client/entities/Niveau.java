package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/03/15 Time: 10:45 To change
 * this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "niveau")
public class Niveau extends AbstractDateEntity implements Serializable {

    public enum ETAGE {

        RDC("RDC"),
        ETAGE_1("1er"),
        ETAGE_2("2eme"),
        ETAGE_3("3eme"),
        ETAGE_4("4eme"),
        ETAGE_5("5eme"),
        ETAGE_6("6eme"),
        ETAGE_7("7eme");

        String desc;

        private ETAGE(String desc) {
            this.desc = desc;
        }

        public String getLabel() {
            return this.desc;
        }

        public String getKey() {
            return this.name();
        }

    };

    public enum ETAT {

        EXCELLENT("Excellent"),
        TBIEN("Très Bien"),
        MOYEN("Moyen"),
        DELABRE("Délabré"),;

        String label;

        private ETAT(String desc) {
            this.label = desc;
        }

        public String getLabel() {
            return this.label;
        }

        public String getKey() {
            return this.name();
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_niveau")
    private Long id;

    @Column(name = "libelle")
    private String libelle;
    @Column(name = "etage")
    @Enumerated(EnumType.STRING)
    private ETAGE etage;

    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private ETAT etat;

    @Column(name = "superficie")
    private String superficie;
    @Column(name = "camera")
    private boolean camera;
    @Column(name = "ascenseur")
    private boolean ascenseur;
    @Column(name = "extincteur")
    private boolean extincteur;
    @Column(name = "wifi")
    private boolean wifi;

    @ManyToOne
    @JoinColumn(name = "id_batiment")
    private BatimentLocalite batiment;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "niveau_caracteristique", joinColumns = @JoinColumn(name = "ID_NIVEAU"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARACTERISTIQUE"))
    private List<Caracteristique> caracteristiques;

    public Niveau() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEtageName() {
        if (etage != null) {
            return etage.name();
        }
        return null;
    }
    
    public ETAGE getEtage() {        
            return etage;       
    }

    public void setEtage(String etage) {
        this.etage = ETAGE.valueOf(etage);
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public boolean isAscenseur() {
        return ascenseur;
    }

    public void setAscenseur(boolean ascenseur) {
        this.ascenseur = ascenseur;
    }

    public boolean isExtincteur() {
        return extincteur;
    }

    public void setExtincteur(boolean extincteur) {
        this.extincteur = extincteur;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public BatimentLocalite getBatiment() {
        return batiment;
    }

    public void setBatiment(BatimentLocalite batiment) {
        this.batiment = batiment;
    }

    public void setEtage(ETAGE etage) {
        this.etage = etage;
    }

    public ETAT getEtat() {
        return etat;
    }

    public static Map<String, String> getEtatCollection() {
        Map<String, String> resultat = new LinkedHashMap<String, String>();
            for (ETAT et : ETAT.values()) {
                resultat.put(et.getKey(), et.getLabel());
            }
        return resultat;
    }

    public static Map<String, String> getEtageCollection() {
        Map<String, String> resultat = new LinkedHashMap<String, String>();
         
                for (ETAGE et : ETAGE.values()) {
                    resultat.put(et.getKey(), et.getLabel());
                }
     
        return resultat;
    }

    public List<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(List<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public void setEtat(ETAT etat) {
        this.etat = etat;
    }

}
