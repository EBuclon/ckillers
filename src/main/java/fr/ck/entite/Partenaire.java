package fr.ck.entite;

/**
 * Class Partenaire
 */
public class Partenaire {
    private Integer idPartenaire;
    private String nom;
    private String description;
    private Inscrit inscrit;

    /**
     * Constructeur 1
     * @param idPartenaire
     * @param nom
     * @param description
     * @param inscrit
     */
    public Partenaire(Integer idPartenaire, String nom, String description, Inscrit inscrit) {
        this.idPartenaire = idPartenaire;
        this.nom = nom;
        this.description = description;
        this.inscrit = inscrit;
    }

    /**
     * Constructeur 2
     * @param nom
     * @param description
     * @param inscrit
     */
    public Partenaire(String nom, String description, Inscrit inscrit) {
        this.nom = nom;
        this.description = description;
        this.inscrit = inscrit;
    }

    /**
     * retourne l'id du partenaire
     * @return
     */
    public Integer getIdPartenaire() {
        return idPartenaire;
    }

    /**
     * permet de modifier l'id du partenaire
     * @param idPartenaire
     */
    public void setIdPartenaire(Integer idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    /**
     * permet d'obtenir le nom du partenaire
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * permet de modifier le nom du partenaire
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * rertourne la description du partenaire
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * permet de modifier la description du partenaire
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * retourne le modérateur qui a ajouté le partenaire
     * @return
     */
    public Inscrit getInscrit() {
        return inscrit;
    }

    /**
     * permet de modifier le modérateur qui a ajouté le partenaire
     * @param inscrit
     */
    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }
}
