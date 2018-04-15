package fr.ck.entite;

/**
 * Class Partie
 */
public class Partie {

    private Integer idPartie;
    private String nomScenario;
    private String nomJeu;
    private Integer nbMin;
    private Integer nbMax;
    private String desUtil;
    private String typeSoiree;
    private String genre;
    private String type;
    private String ton;
    private String inspiration;
    private String niveauAttendu;
    private String presentation;
    private Creneau creneau;
    private Inscrit inscrit;
    private Inscrit inscritV;

    /**
     * Constructeur 1
     * @param idPartie
     * @param nomScenario
     * @param nomJeu
     * @param nbMin
     * @param nbMax
     * @param desUtil
     * @param typeSoiree
     * @param genre
     * @param type
     * @param ton
     * @param inspiration
     * @param niveauAttendu
     * @param presentation
     * @param creneau
     * @param inscrit
     */
    public Partie(Integer idPartie, String nomScenario, String nomJeu, Integer nbMin,
                  Integer nbMax, String desUtil, String typeSoiree, String genre,
                  String type, String ton, String inspiration, String niveauAttendu,
                  String presentation, Creneau creneau, Inscrit inscrit) {
        this.idPartie=idPartie;
        this.nomScenario = nomScenario;
        this.nomJeu = nomJeu;
        this.nbMin = nbMin;
        this.nbMax = nbMax;
        this.desUtil = desUtil;
        this.typeSoiree = typeSoiree;
        this.genre = genre;
        this.type = type;
        this.ton = ton;
        this.inspiration = inspiration;
        this.niveauAttendu = niveauAttendu;
        this.presentation = presentation;
        this.creneau = creneau;
        this.inscrit = inscrit;
    }

    /**
     * Constructeur 2
     * @param idPartie
     * @param nomScenario
     * @param nomJeu
     * @param creneau
     * @param inscrit
     */
    public Partie(Integer idPartie, String nomScenario, String nomJeu, Creneau creneau, Inscrit inscrit) {
        this.idPartie=idPartie;
        this.nomScenario = nomScenario;
        this.nomJeu = nomJeu;
        this.creneau = creneau;
        this.inscrit = inscrit;
    }

    /**
     * Constructeur 3
     * @param idPartie
     * @param nomScenario
     * @param nomJeu
     * @param creneau
     */
    public Partie(Integer idPartie, String nomScenario, String nomJeu, Creneau creneau) {
        this.idPartie=idPartie;
        this.nomScenario = nomScenario;
        this.nomJeu = nomJeu;
        this.creneau = creneau;
    }

    /**
     * retourne l'id de la partie
     * @return
     */
    public Integer getIdPartie() {
        return idPartie;
    }

    /**
     * permet de modifier l'id de la partie
     * @param id
     */
    public void setIdPartie(Integer id) {
        this.idPartie = id;
    }

    /**
     * retourne le nom du scénario
     * @return
     */
    public String getNomScenario() {
        return nomScenario;
    }

    /**
     * permet de modifier le nom du scénario
     * @param nomScenario
     */
    public void setNomScenario(String nomScenario) {
        this.nomScenario = nomScenario;
    }

    /**
     * retourne le nom du jeu
     * @return
     */
    public String getNomJeu() {
        return nomJeu;
    }

    /**
     * permet de modifier le nom du jeu
     * @param nomJeu
     */
    public void setNomJeu(String nomJeu) {
        this.nomJeu = nomJeu;
    }

    /**
     * retourne le nombre minimum de joueur
     * @return
     */
    public Integer getNbMin() {
        return nbMin;
    }

    /**
     * permet de modifier le nombre minimum de joueur
     * @param nbMin
     */
    public void setNbMin(Integer nbMin) {
        this.nbMin = nbMin;
    }

    /**
     * retourne le nombre max de joueur
     * @return
     */
    public Integer getNbMax() {
        return nbMax;
    }

    /**
     * permet de modifier le nombre max de joueur
     * @param nbMax
     */
    public void setNbMax(Integer nbMax) {
        this.nbMax = nbMax;
    }

    /**
     * retourne les dès utilisés
     * @return
     */
    public String getDesUtil() {
        return desUtil;
    }

    /**
     * permet de changer les dés utilisés
     * @param desUtil
     */
    public void setDesUtil(String desUtil) {
        this.desUtil = desUtil;
    }

    /**
     * retourne le type de soiree
     * @return
     */
    public String getTypeSoiree() {
        return typeSoiree;
    }

    /**
     * permet de modifier le type de soirée
     * @param typeSoiree
     */
    public void setTypeSoiree(String typeSoiree) {
        this.typeSoiree = typeSoiree;
    }

    /**
     * retourne le genre de la partie
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * permet de modifier le genre de la partie
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * retourne le type de la partie
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * permet de modifier le type de la soirée
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * retourne le ton de la partie
     * @return
     */
    public String getTon() {
        return ton;
    }

    /**
     * permet de modifier le ton de la partie
     * @param ton
     */
    public void setTon(String ton) {
        this.ton = ton;
    }

    /**
     * permet d'obtenir l'inspiration de la partie
     * @return
     */
    public String getInspiration() {
        return inspiration;
    }

    /**
     * permet de changer l'inspiration de la partie
     * @param inspiration
     */
    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    /**
     * retourne le niveau attendu des joueurs
     * @return
     */
    public String getNiveauAttendu() {
        return niveauAttendu;
    }

    /**
     * permet de changer le niveau attendu des joueurs
     * @param niveauAttendu
     */
    public void setNiveauAttendu(String niveauAttendu) {
        this.niveauAttendu = niveauAttendu;
    }

    /**
     * retourne la description de la partie
     * @return
     */
    public String getPresentation() {
        return presentation;
    }

    /**
     * permet de modifier la description de la partie
     * @param presentation
     */
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     * retoune le creneau associé à la partie
     * @return
     */
    public Creneau getCreneau() {
        return creneau;
    }

    /**
     * permet de modifier le creneau de la partie
     * @param creneau
     */
    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

    /**
     * retourne l'inscrit qui a édité la partie
     * @return
     */
    public Inscrit getInscrit() {
        return inscrit;
    }

    /**
     * permet de modifier l'inscrit qui a édité la partie
     * @param inscrit
     */
    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }

    /**
     * retourne le modérateur qui a validé la partie
     * @return
     */
    public Inscrit getInscritV() {
        return inscritV;
    }

    /**
     * permet de modifier le modérateur qui a modifié la partie
     * @param inscritV
     */
    public void setInscritV(Inscrit inscritV) {
        this.inscritV = inscritV;
    }
}
