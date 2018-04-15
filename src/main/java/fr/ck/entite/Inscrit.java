package fr.ck.entite;

/**
 * Class Inscrit ( les membres du site internet)
 */
public class Inscrit {

    private Integer idInscrit;
    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private String adresse;
    private String statut;
    private String dateAdhesion;
    private String dateInscription;
    private Integer nbrPartieJouees;
    private String motDePasse;

    /**
     * Constructeur 1
     * @param idInscrit
     * @param nom
     * @param prenom
     * @param mail
     * @param telephone
     * @param adresse
     * @param statut
     * @param dateAdhesion
     * @param dateInscription
     * @param nbrPartieJouees
     * @param motDePasse
     */
    public Inscrit(Integer idInscrit, String nom, String prenom, String mail, String telephone, String adresse,
                   String statut, String dateAdhesion, String dateInscription, Integer nbrPartieJouees, String motDePasse) {
        this.idInscrit = idInscrit;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.adresse = adresse;
        this.statut = statut;
        this.dateAdhesion = dateAdhesion;
        this.dateInscription = dateInscription;
        this.nbrPartieJouees = nbrPartieJouees;
        this.motDePasse = motDePasse;
    }

    /**
     * Constructeur 2
     * @param idInscrit
     * @param nom
     * @param prenom
     */
    public Inscrit(Integer idInscrit, String nom, String prenom){
        this.idInscrit = idInscrit;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Constructeur 3
     * @param idInscrit
     * @param nom
     * @param prenom
     * @param mail
     */
    public Inscrit(Integer idInscrit, String nom, String prenom, String mail){
        this.idInscrit = idInscrit;
        this.nom = nom;
        this.prenom = prenom;
        this.mail=mail;
    }

    public Inscrit(){

    };

    /**
     * Constructeur 4
     * @param statut
     */
    public Inscrit(String statut){
        this.statut=statut;
    };


    /**
     * Constructeur 5
     * @param mail
     * @param statut
     */
   public Inscrit(String mail, String statut){
        this.mail=mail;
        this.statut=statut;
    }

    /**
     * Constructeur 6
     * @param mail
     * @param motDePasse
     * @param statut
     */
   public Inscrit(String mail, String motDePasse, String statut){
        this.mail=mail;
        this.motDePasse=motDePasse;
        this.statut=statut;
    }

    /**
     * Constructeur 7
     * @param idInscrit
     */
    public Inscrit(Integer idInscrit){
        this.idInscrit=idInscrit;
    }


    /**
     * retourne l'id de l'inscrit
     * @return
     */
    public Integer getIdInscrit() {
        return idInscrit;
    }

    /**
     * permet de modifier l'id de l'inscrit
     * @param idInscrit
     */
    public void setIdInscrit(Integer idInscrit) {
        this.idInscrit = idInscrit;
    }

    /**
     * retourne le nom de l'inscrit
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * permet de modifier le nom de l'inscrit
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * retourne le prenom de l'inscrit
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * permet de modifier le prenom de l'inscrit
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * retourne le mail de l'inscrit
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     * permet de modifier le mail de l'inscrit
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * retourne le téléphone de l'inscrit
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * permet de modifier le numéro de téléphone de l'inscrit
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * retourne l'adresse de l'inscrit
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * permet de modifier l'adresse de l'inscrit
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * retourne le statut de l'inscrit
     * @return
     */
    public String getStatut() {
        return statut;
    }

    /**
     * permet de modifier le statut de l'inscrit
     * @param statut
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * permet d'obtenir la date d'adhésion
     * @return
     */
    public String getDateAdhesion() {
        return dateAdhesion;
    }

    /**
     * permet de modifier la date d'adhésion
     * @param dateAdhesion
     */
    public void setDateAdhesion(String dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    /**
     * retourne la date d'inscription au site internet
     * @return
     */
    public String getDateInscription() {
        return dateInscription;
    }

    /**
     * permet de modifier la date d'inscription
     * @param dateInscription
     */
    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    /**
     * retourne le nb de parties jouées
     * @return
     */
    public Integer getNbrPartieJouees() {
        return nbrPartieJouees;
    }

    /**
     * permet de modifier le nombre de parties jouées dans l'année
     * @param nbrPartieJouees
     */
    public void setNbrPartieJouees(Integer nbrPartieJouees) {
        this.nbrPartieJouees = nbrPartieJouees;
    }

    /**
     * retourne le mot de Passe de l'inscrit
     * @return
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * permet de modifier le mot de passe
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
