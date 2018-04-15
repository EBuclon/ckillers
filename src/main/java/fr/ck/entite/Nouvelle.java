package fr.ck.entite;

/**
 * Class Nouvelle
 */
public class Nouvelle {
    private Integer idNouvelle;
    private String titre;
    private String texte;
    private Inscrit inscrit;

    /**
     * Constructeur 1
     * @param idNouvelle
     * @param titre
     * @param texte
     * @param inscrit
     */
    public Nouvelle(Integer idNouvelle, String titre, String texte, Inscrit inscrit) {
        this.idNouvelle = idNouvelle;
        this.titre = titre;
        this.texte = texte;
        this.inscrit = inscrit;
    }

    /**
     * Constructeur 2
     * @param titre
     * @param texte
     * @param inscrit
     */
    public Nouvelle(String titre, String texte, Inscrit inscrit) {
        this.titre = titre;
        this.texte = texte;
        this.inscrit = inscrit;
    }

    /**
     * retourne l'id de la nouvelle
     * @return
     */
    public Integer getIdNouvelle() {
        return idNouvelle;
    }

    /**
     * permet de modifier l'id de la nouvelle
     * @param idNouvelle
     */
    public void setIdNouvelle(Integer idNouvelle) {
        this.idNouvelle = idNouvelle;
    }

    /**
     * retourne l'inscrit qui a écrit la nouvelle
     * @return
     */
    public Inscrit getInscrit() {
        return inscrit;
    }

    /**
     * rpermet de modifier l'inscrit qui a ajouté la nouvelle
     * @param inscrit
     */
    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }

    /**
     * retourne le titre de la nouvelle
     * @return
     */
    public String getTitre() {
        return titre;
    }

    /**
     * permet de modifier le titre de la nouvelle
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * retourne le texte de la nouvelle
     * @return
     */
    public String getTexte() {
        return texte;
    }

    /**
     * permet de modifier le texte de la nouvelle
     * @param texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

}
