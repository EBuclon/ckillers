package fr.ck.entite;


public class Creneau {

    private Integer idCreneau;
    private String date;
    private String heure;
    private String lieu;
    private Inscrit incrit;

    public Creneau(Integer idCreneau, String date, String heure, String lieu, Inscrit incrit) {
        this.idCreneau = idCreneau;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.incrit = incrit;
    }

    public Creneau(Integer idCreneau){
        this.idCreneau=idCreneau;
    }

    public Integer getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(Integer idCreneau) {
        this.idCreneau = idCreneau;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Inscrit getIncrit() {
        return incrit;
    }

    public void setIncrit(Inscrit incrit) {
        this.incrit = incrit;
    }
}
