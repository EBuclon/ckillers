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

    public void ajouterPartie(Partie partie) {
        partieDao.ajouterPartie(partie);
    }








    public void addInscrit(Inscrit inscrit){
        inscritDao.addInscrit(inscrit);
    }

}