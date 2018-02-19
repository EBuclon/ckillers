package fr.ck.entite;

public class Evenement {

    private Integer id_Event;
    private String titre;
    private String contenu;
    private Integer id_Creneau;

    public Evenement(Integer id_Event, String titre, String contenu, Integer id_Creneau) {
        this.id_Event = id_Event;
        this.titre = titre;
        this.contenu = contenu;
        this.id_Creneau = id_Creneau;
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

    public Integer getId_Creneau() {
        return id_Creneau;
    }

    public void setId_Creneau(Integer id_Creneau) {
        this.id_Creneau = id_Creneau;
    }
}
