package fr.ck.entite;

/**
 * Class Creneau
 */
public class Creneau {

    private Integer idCreneau;
    private String date;
    private String heure;
    private String lieu;
    private Inscrit inscrit;

    /**
     * Constructeur 1
     * @param idCreneau
     * @param date
     * @param heure
     * @param lieu
     * @param inscrit
     */
    public Creneau(Integer idCreneau, String date, String heure, String lieu, Inscrit inscrit) {
        this.idCreneau = idCreneau;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.inscrit = inscrit;
    }

    /**
     * Constructeur 2
     * @param date
     * @param heure
     * @param lieu
     */
     public Creneau(String date, String heure, String lieu) {
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
    }

    public Creneau(Integer idCreneau){
        this.idCreneau=idCreneau;
    }

    /**
     * Getter retournant l'id du Creneau
     * @return
     */
    public Integer getIdCreneau() {
        return idCreneau;
    }

    /**
     * Setter permettant de changer l'id du Creneau
     * @param idCreneau
     */
    public void setIdCreneau(Integer idCreneau) {
        this.idCreneau = idCreneau;
    }

    /**
     * Getter retournant la date du Creneau en String
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter permettant de modifier la date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter retournant la plage horraire en String
     * @return
     */
    public String getHeure() {
        return heure;
    }

    /**
     * Setter permettant de changer la plage horraire
     * @param heure
     */
    public void setHeure(String heure) {
        this.heure = heure;
    }

    /**
     * Getter retournant le lieu
     * @return
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Setter permettant de modifier le lieu
     * @param lieu
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    /**
     * Getter retournant l'utilisateur qui a mis en ligne le creneau
     * @return
     */
    public Inscrit getInscrit() {
        return inscrit;
    }

    /**
     * Setter qui permet de modifier l'utilisateur qui a mis en ligne le creneau
     * @param inscrit
     */
    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }
}
