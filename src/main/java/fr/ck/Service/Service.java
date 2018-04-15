package fr.ck.Service;

import fr.ck.daos.*;
import fr.ck.entite.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * Classe d'instanciation des DAOs. Permet de maintenir l'instance.
 * Chaque fonction renvoie à son homonyme du DAO associé.
 */
public class Service {

    private static final String IMAGE_DIRECTORY_PATH = "C:/Developpement Web/ck/src/main/webapp/image";

    private PartieDao partieDao = new PartieDao();
    private CreneauDao creneauDao = new CreneauDao();
    private InscritDao inscritDao = new InscritDao();
    private ParticipationDao participationDao = new ParticipationDao();
    private NouvelleDao nouvelleDao = new NouvelleDao();
    private PartenaireDao partenaireDao = new PartenaireDao();
    private EvenementDao evenementDao = new EvenementDao();
    private AllerDao allerDao = new AllerDao();


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

    public List<Partie> listPartiesEvenements() {
        return creneauDao.listPartiesEvenements();
    }

    public Creneau getCreneau(Integer idCreneau) {
        return creneauDao.getCreneau(idCreneau);
    }

    public void supprimerCreneau(Integer idCreneau) {
        creneauDao.supprimerCreneau(idCreneau);
    }


    public void ajouterPartie(Partie partie, Part image) {
        if (partie == null) {
            throw new IllegalArgumentException("Partie inexistante");
        }

        String nomImage = chargerImage(image);
        partieDao.ajouterPartie(partie, nomImage);
    }

    public void validerPartie(Partie partie, Part image, Integer idInscrit) {
        String nomImage = chargerImage(image);
        partieDao.validerPartie(partie, nomImage, idInscrit);
    }

    /**
     * Fonction pour charger les images sur le serveur
     * @param image
     * @return
     */
    private String chargerImage(Part image) {
        String nomFichier = null;
        System.out.println(image.getSubmittedFileName());
        if (!image.getSubmittedFileName().equals("")) {
            String nomImage = image.getSubmittedFileName();
            if(image.getSubmittedFileName().length()>20){
                nomImage = nomImage.substring(0,20);
            }
            nomFichier = UUID.randomUUID().toString().substring(0, 8) + "-" + nomImage;
            Path cheminImage = Paths.get(IMAGE_DIRECTORY_PATH, nomFichier);
            try {
                Files.copy(image.getInputStream(), cheminImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nomFichier;
    }

    public List<Partie> listPartieValide() {
        return partieDao.listPartieValide();
    }

    public List<Partie> listPartieEnAttente() {
        return partieDao.listPartieEnAttente();
    }

    public List<Partie> listePartieParJoueur(Integer id) {
        return partieDao.listPartieParJoueur(id);
    }

    public Partie getPartie(Integer idPartie) {
        return partieDao.getPartie(idPartie);
    }

    public Path getImage(Integer idPartie) {
        String image = partieDao.getImage(idPartie);
        Path chemin;
        if (image.equals("")) {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/banner.jpg");
        } else {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/" + image);
        }
        return chemin;
    }

    public void supprimerPartie(Integer idPartie, Integer idCreneau) {
        partieDao.supprimerPartie(idPartie, idCreneau);
    }

    /**
     * Change le format de la date pour l'adapter à la base de données
     * @param date
     * @return
     */
    public List<Partie> listePartiesParJour(String date) {
        String dateT;
        String[] parts = date.split("/");
        dateT = parts[0] + "-" + parts[1] + "-" + parts[2];
        return partieDao.listePartiesParJour(dateT);
    }

    public List<Integer> listeDateAvecPartie(String mois, String annee) {
        return creneauDao.listeDateAvecPartie(mois, annee);
    }


    public Inscrit getConnexion(String mail) {
        return inscritDao.getConnexion(mail);
    }

    public List<Inscrit> listInscrit() {
        return inscritDao.listInscrit();
    }

    public void modifierStatutInscrit(Integer id, String statut) {
        inscritDao.modifierStatutInscrit(id, statut);
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


    public void ajouterNouvelle(Nouvelle nouvelle) {
        nouvelleDao.ajouterNouvelle(nouvelle);
    }

    public List<Nouvelle> listNouvelle() {
        return nouvelleDao.listNouvelle();
    }

    public void supprimerNouvelle(Integer idNouvelle) {
        nouvelleDao.supprimerNouvelle(idNouvelle);
    }


    public void ajouterPartenaire(Partenaire nouvelle) {
        partenaireDao.ajouterPartenaire(nouvelle);
    }

    public List<Partenaire> listPartenaires() {
        return partenaireDao.listPartenaires();
    }

    public void supprimerPartenaire(Integer idPartenaire) {
        partenaireDao.supprimerPartenaire(idPartenaire);
    }

    public Path getImagePartenaire(Integer idEvenement) {
        String image = partenaireDao.getImagePartenaire(idEvenement);
        Path chemin;
        if (image.equals("")) {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/banner.jpg");
        } else {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/" + image);
        }
        return chemin;
    }


    public void ajouterEvent(Evenement evenement) {
        evenementDao.ajouterEvent(evenement);
    }

    public Evenement getEvenement(Integer idEvenement){
        return evenementDao.getEvenement(idEvenement);
    }

    public Path getImageEvent(Integer idEvenement) {
        String image = evenementDao.getImageEvent(idEvenement);
        Path chemin;
        if (image.equals("")) {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/banner.jpg");
        } else {
            chemin = Paths.get(IMAGE_DIRECTORY_PATH + "/" + image);
        }
        return chemin;
    }

    public void ajouterImageE(Integer idEvenement, Part image) {
        if (idEvenement == null) {
            throw new IllegalArgumentException("Partie inexistante");
        }

        String nomImage = chargerImage(image);
        evenementDao.ajouterImageE(idEvenement, nomImage);
    }

    public void supprimerEvenement(Integer idEvenement, Integer idCreneau) {
        evenementDao.supprimerEvenement(idEvenement, idCreneau);
    }


    public void aller(Integer idEvenement, Integer idInscrit) {
        allerDao.aller(idEvenement, idInscrit);
    }

    public void annulerAller(Integer idEvenement, Integer idInscrit) {
        allerDao.annulerAller(idEvenement, idInscrit);
    }

    public List<Inscrit> listeAllants(Integer idEvenement) {
        return allerDao.listeAllants(idEvenement);
    }

}