package fr.ck.Service;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.InscritDao;
import fr.ck.daos.ParticipationDao;
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
    private ParticipationDao participationDao = new ParticipationDao();


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

    public void supprimerCreneau(Integer idCreneau) {
        creneauDao.supprimerCreneau(idCreneau);
    }

    public void ajouterPartie(Partie partie) {
        partieDao.ajouterPartie(partie);
    }

    public void validerPartie(Partie partie, Integer idInscrit) {
        partieDao.validerPartie(partie, idInscrit);
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

    public void supprimerPartie(Integer idPartie, Integer idCreneau) {
        partieDao.supprimerPartie(idPartie, idCreneau);
    }



    public Inscrit getConnexion(String mail) {
        return inscritDao.getConnexion(mail);
    }

    public void ajouterInscrit(Inscrit inscrit) {
        inscritDao.ajouterInscrit(inscrit);
    }

    public Inscrit getInscritParMail(String mail) {
        return inscritDao.getInscritParMail(mail);
    }

    public Integer getIdParMail(String mail) {
        return inscritDao.getIdParMail(mail);
    }

    public Inscrit getInscritValideur(Integer idPartie) {
        return inscritDao.getInscritValideur(idPartie);
    }

    public void participer(Integer idPartie, Integer idInscrit) {
        participationDao.participer(idPartie, idInscrit);
    }

    public void annulerParticiper(Integer idPartie, Integer idInscrit) {
        participationDao.annulerParticiper(idPartie, idInscrit);
    }

    public List<Inscrit> listeParticipants(Integer idPartie) {
        return participationDao.listeParticipants(idPartie);
    }
}