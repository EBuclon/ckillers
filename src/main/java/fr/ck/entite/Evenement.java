package fr.ck.entite;

/**
 * Class Evenement
 */
public class Evenement {

    private Integer id_Event;
    private String titre;
    private String contenu;
    private Creneau Creneau;

    /**
     * Constructeur 1
     * @param id_Event
     * @param titre
     * @param contenu
     * @param creneau
     */
    public Evenement(Integer id_Event, String titre, String contenu, Creneau creneau) {
        this.id_Event = id_Event;
        this.titre = titre;
        this.contenu = contenu;
        this.Creneau = creneau ;
    }

    /**
     * Constructeur 2
     * @param titre
     * @param contenu
     * @param creneau
     */
    public Evenement(String titre, String contenu, Creneau creneau) {
        this.titre = titre;
        this.contenu = contenu;
        this.Creneau = creneau ;
    }

    /**
     * retourne l'id de l'évènement
     * @return
     */
    public Integer getId_Event() {
        return id_Event;
    }

    /**
     * Permet de changer l'id de l'évènement
     * @param id_Event
     */
    public void setId_Event(Integer id_Event) {
        this.id_Event = id_Event;
    }

    /**
     * retourne le titre de l'évènement
     * @return
     */
    public String getTitre() {
        return titre;
    }

    /**
     * permet de changer le titre de l'évènement
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * retourne la description de l'évènement
     * @return
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * permet de changer la description de l'évènement
     * @param contenu
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * permet d'obtenir le creneau associé à l'évènement
     * @return
     */
    public fr.ck.entite.Creneau getCreneau() {
        return Creneau;
    }

    /**
     * permet de modifier le creneau associé à l'évènement
     * @param creneau
     */
    public void setCreneau(fr.ck.entite.Creneau creneau) {
        Creneau = creneau;
    }
}
