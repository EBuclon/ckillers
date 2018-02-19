package fr.ck.entite;

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
    private Integer idInscrit;
    private Integer idInscritV;

    public Partie(Integer id, String nomScenario, String nomJeu, Integer nbMin,
                  Integer nbMax, String desUtil, String typeSoiree, String genre,
                  String type, String ton, String inspiration, String niveauAttendu,
                  String presentation, Creneau creneau, Integer idInscrit, Integer idInscritV) {
        this.idPartie=id;
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
        this.idInscrit = idInscrit;
        this.idInscritV = idInscritV;
    }

    public Integer getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Integer id) {
        this.idPartie = id;
    }

    public String getNomScenario() {
        return nomScenario;
    }

    public void setNomScenario(String nomScenario) {
        this.nomScenario = nomScenario;
    }

    public String getNomJeu() {
        return nomJeu;
    }

    public void setNomJeu(String nomJeu) {
        this.nomJeu = nomJeu;
    }

    public Integer getNbMin() {
        return nbMin;
    }

    public void setNbMin(Integer nbMin) {
        this.nbMin = nbMin;
    }

    public Integer getNbMax() {
        return nbMax;
    }

    public void setNbMax(Integer nbMax) {
        this.nbMax = nbMax;
    }

    public String getDesUtil() {
        return desUtil;
    }

    public void setDesUtil(String desUtil) {
        this.desUtil = desUtil;
    }

    public String getTypeSoiree() {
        return typeSoiree;
    }

    public void setTypeSoiree(String typeSoiree) {
        this.typeSoiree = typeSoiree;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTon() {
        return ton;
    }

    public void setTon(String ton) {
        this.ton = ton;
    }

    public String getInspiration() {
        return inspiration;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public String getNiveauAttendu() {
        return niveauAttendu;
    }

    public void setNiveauAttendu(String niveauAttendu) {
        this.niveauAttendu = niveauAttendu;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

    public Integer getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(Integer idInscrit) {
        this.idInscrit = idInscrit;
    }

    public Integer getIdInscritV() {
        return idInscritV;
    }

    public void setIdInscritV(Integer idInscritV) {
        this.idInscritV = idInscritV;
    }
}
