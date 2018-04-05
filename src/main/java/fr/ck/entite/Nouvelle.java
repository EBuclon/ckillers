package fr.ck.entite;

public class Nouvelle {
    private String titre;
    private String texte;
    private Integer idInscrit;

    public Nouvelle(String titre, String texte, Integer idInscrit) {
        this.titre = titre;
        this.texte = texte;
        this.idInscrit = idInscrit;
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

    public Integer getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(Integer idInscrit) {
        this.idInscrit = idInscrit;
    }
}
