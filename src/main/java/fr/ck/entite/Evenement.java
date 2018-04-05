package fr.ck.entite;

public class Evenement {

    private Integer id_Event;
    private String titre;
    private String contenu;
    private Creneau Creneau;

    public Evenement(Integer id_Event, String titre, String contenu, Creneau creneau) {
        this.id_Event = id_Event;
        this.titre = titre;
        this.contenu = contenu;
        this.Creneau = creneau ;
    }

    public Evenement(String titre, String contenu, Creneau creneau) {
        this.titre = titre;
        this.contenu = contenu;
        this.Creneau = creneau ;
    }

    public Integer getId_Event() {
        return id_Event;
    }

    public void setId_Event(Integer id_Event) {
        this.id_Event = id_Event;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public fr.ck.entite.Creneau getCreneau() {
        return Creneau;
    }

    public void setCreneau(fr.ck.entite.Creneau creneau) {
        Creneau = creneau;
    }
}
