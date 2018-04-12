package fr.ck.entite;

public class Nouvelle {
    private Integer idNouvelle;
    private String titre;
    private String texte;
    private Inscrit inscrit;

    public Nouvelle(Integer idNouvelle, String titre, String texte, Inscrit inscrit) {
        this.idNouvelle = idNouvelle;
        this.titre = titre;
        this.texte = texte;
        this.inscrit = inscrit;
    }

    public Nouvelle(String titre, String texte, Inscrit inscrit) {
        this.titre = titre;
        this.texte = texte;
        this.inscrit = inscrit;
    }

    public Integer getIdNouvelle() {
        return idNouvelle;
    }

    public void setIdNouvelle(Integer idNouvelle) {
        this.idNouvelle = idNouvelle;
    }

    public Inscrit getInscrit() {
        return inscrit;
    }

    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

}
