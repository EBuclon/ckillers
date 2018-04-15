package daosTests;

import fr.ck.daos.DataSourceProvider;
import org.junit.Before;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Classe générique des tests, initialise la base de données pour les tests
 */
public class GenericDaoTest {

    @Before
    public void initDb() throws Exception{
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE creneau SET idInscrit=null");
            stmt.executeUpdate("UPDATE creneau SET idPartie=null");
            stmt.executeUpdate("UPDATE partie SET idCreneau=null");
            stmt.executeUpdate("UPDATE partie SET idInscrit=null");
            stmt.executeUpdate("UPDATE partie SET idInscrit_1=null");
            stmt.executeUpdate("UPDATE evenement SET idCreneau=null");

            stmt.executeUpdate("DELETE FROM aller");
            stmt.executeUpdate("DELETE FROM participer");
            stmt.executeUpdate("DELETE FROM nouvelle");
            stmt.executeUpdate("DELETE FROM partenaire");
            stmt.executeUpdate("DELETE FROM inscrit");
            stmt.executeUpdate("DELETE FROM creneau");
            stmt.executeUpdate("DELETE FROM partie");
            stmt.executeUpdate("DELETE FROM evenement");

            stmt.executeUpdate("INSERT INTO inscrit(idInscrit,nom,prenom,mail,telephone,adresse,statut,dateAdhesion,dateInscription,nbrPartieJouees,motDePasse) VALUES (1,'Dylan','Bob','bob@dylan.us','000000000','rue de toul','admin',null,'2018-02-22',0,'xxx')");
            stmt.executeUpdate("INSERT INTO inscrit(idInscrit,nom,prenom,mail,telephone,adresse,statut,dateAdhesion,dateInscription,nbrPartieJouees,motDePasse) VALUES (2,'Dujardin','Jean-François','jf@hei.fr','000000000','rue Colbert','adherent',null,'2018-02-22',0,'xxx')");

            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (1,'2018-05-12','18h-20h','bar tandem',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (2,'2018-08-12','15h-20h','maison des associations',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (3,'2018-09-12','18h-22h','chez Robert',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (4,'2018-09-12','18h-22h','MDA',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (5,'2018-10-25','14h-22h','bar tandem',null,1)");

            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, image, idCreneau, idInscrit,idInscrit_1) values (1,'La peur','Cthulu',2,5,'de6, de8','One shot','Horreur','','Horreur','Lovecraft','Expert','Une petite partie autour du mythe de Cthulu...','cthulu.jpg',1,1,1)");
            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, image, idCreneau, idInscrit,idInscrit_1) values (2,'Pirates','Pathfinder',3,5,'de6, 12, 20','Campagne fermée','Aventure','...','Leger','Medieval-Fantastique','Confirme, Expert','Des pirates égaré en mer','',3,2,null)");
            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, image, idCreneau, idInscrit,idInscrit_1) values (3,'Mon petit poney','Mon petit poney',4,8,'de6, 12, 20','One shot','Humour','...','Leger','Fantastique','Debutant, Confirme, Expert','Pour rigoler entre amis','',2,2,null)");

            stmt.executeUpdate("INSERT INTO participer(idPartie, idInscrit) values (1,1)");

            stmt.executeUpdate("INSERT INTO evenement(idEvenement, titre, contenu, imageE, idCreneau) values (1,'Open JDR','Beaucoup de joueurs attendus!','imageOpenJdr.png', 5)");

            stmt.executeUpdate("INSERT INTO aller(idEvenement, idInscrit) values (1,1)");

            stmt.executeUpdate("INSERT INTO nouvelle(idNouvelle, titreNouvelle, texte, imageN, idInscrit) values (1,'Bonne nouvelle','Site bientot fini!', '', 1)");

            stmt.executeUpdate("INSERT INTO partenaire(idPartenaire, nomPartenaire, descriptionPartenaire, imageP, idInscrit) values (1,'Le Bazar du Bizarre','Marchant de jeux en tout genres', 'imagePartenaire', 1)");

            stmt.executeUpdate("UPDATE creneau SET idPartie=1 WHERE idCreneau=1");
            stmt.executeUpdate("UPDATE creneau SET idPartie=3 WHERE idCreneau=2");
            stmt.executeUpdate("UPDATE creneau SET idPartie=2 WHERE idCreneau=3");
            stmt.executeUpdate("UPDATE creneau SET idEvenement=1 WHERE idCreneau=5");
        }
    }

}
