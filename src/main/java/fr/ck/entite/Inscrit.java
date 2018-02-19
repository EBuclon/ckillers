package fr.ck.entite;

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

    public Inscrit(Integer idInscrit, String nom,
                   String prenom, String mail,
                   String telephone, String adresse,
                   String statut, String dateAdhesion,
                   String dateInscription,
                   Integer nbrPartieJouees,
                   String motDePasse) {
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

    public Integer getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(Integer idInscrit) {
        this.idInscrit = idInscrit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(String dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Integer getNbrPartieJouees() {
        return nbrPartieJouees;
    }

    public void setNbrPartieJouees(Integer nbrPartieJouees) {
        this.nbrPartieJouees = nbrPartieJouees;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
