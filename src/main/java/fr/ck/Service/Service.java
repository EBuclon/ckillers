package fr.ck.Service;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.InscritDao;
import fr.ck.daos.PartieDao;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;

import java.util.List;

public class Service {

    private static final String IMAGE_DIRECTORY_PATH = "C:/Developpement Web/ck/src/main/webapp/img";

    private PartieDao partieDao = new PartieDao();
    private CreneauDao creneauDao = new CreneauDao();
    private InscritDao inscritDao = new InscritDao();


    private static class ServiceHolder {
        private static Service instance = new Service();
    }

    public static Service getInstance() {
        return ServiceHolder.instance;
    }

    private Service() {
    }

    public void ajouterCreneau(Creneau creneau) {
        if (creneau == null) {
            throw new IllegalArgumentException("Pas de creneau.");
        }
        creneauDao.ajouterCreneau(creneau);
    }

    public List<Creneau> listCreneauxLibres() {
        return creneauDao.listCreneauxLibres();
    }

    public Creneau getCreneau(Integer idCreneau) {
        return creneauDao.getCreneau(idCreneau);
    }

    public void supprimerCreneau(Integer idCreneau){creneauDao.supprimerCreneau(idCreneau);}

    public void ajouterPartie(Partie partie) {
        partieDao.ajouterPartie(partie);
    }

    public List<Partie> listPartieValide() {
        return partieDao.listPartieValide();
    }

    public List<Partie> listPartieEnAttente() {
        return partieDao.listPartieEnAttente();
    }

    public Partie getPartie(Integer idPartie) {
        return partieDao.getPartie(idPartie);
    }






    public String getConnexion(String mail) {
        return inscritDao.getConnexion(mail);
    }

    public void ajouterInscrit(Inscrit inscrit) {
        inscritDao.ajouterInscrit(inscrit);
    }

    public Inscrit getInscritParMail(String mail) {
        return inscritDao.getInscritParMail(mail);
    }
}