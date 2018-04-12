package fr.ck.entite;

public class Partenaire {
    private Integer idPartenaire;
    private String nom;
    private String description;
    private Inscrit inscrit;

    public Partenaire(Integer idPartenaire, String nom, String description, Inscrit inscrit) {
        this.idPartenaire = idPartenaire;
        this.nom = nom;
        this.description = description;
        this.inscrit = inscrit;
    }

    public Partenaire(String nom, String description, Inscrit inscrit) {
        this.nom = nom;
        this.description = description;
        this.inscrit = inscrit;
    }

    public Integer getIdPartenaire() {
        return idPartenaire;
    }

    public void setIdPartenaire(Integer idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inscrit getInscrit() {
        return inscrit;
    }

    public void setInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }
}
