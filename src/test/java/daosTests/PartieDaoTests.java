package daosTests;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.daos.PartieDao;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PartieDaoTests {

    private PartieDao partieDao = new PartieDao();
    private CreneauDao creneauDao = new CreneauDao();

    @Before
    public void initDb() throws Exception{
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("UPDATE creneau SET idInscrit=null");
            stmt.executeUpdate("UPDATE creneau SET idPartie=null");
            stmt.executeUpdate("UPDATE partie SET idCreneau=null");
            stmt.executeUpdate("UPDATE partie SET idInscrit=null");
            stmt.executeUpdate("UPDATE partie SET idInscrit_1=null");

            stmt.executeUpdate("DELETE FROM inscrit");
            stmt.executeUpdate("DELETE FROM creneau");
            stmt.executeUpdate("DELETE FROM partie");

            stmt.executeUpdate("INSERT INTO inscrit(idInscrit,nom,prenom,mail,telephone,adresse,statut,dateAdhesion,dateInscription,nbrPartieJouees,motDePasse) VALUES (1,'Dylan','Bob','bob@dylan.us',null,'rue de toul','admin',null,'2018-02-22',0,'xxx')");
            stmt.executeUpdate("INSERT INTO inscrit(idInscrit,nom,prenom,mail,telephone,adresse,statut,dateAdhesion,dateInscription,nbrPartieJouees,motDePasse) VALUES (2,'Dujardin','Jean-François','jf@hei.fr',null,'rue Colbert','adherent',null,'2018-02-22',0,'xxx')");

            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (1,'2012-05-12','18h-20h','bar tandem',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (2,'2015-08-12','15h-20h','maison des associations',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (3,'2011-09-12','18h-22h','chez Robert',null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idPartie,idInscrit) VALUES (4,'2011-09-12','18h-22h','MDA',null,1)");

            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, idCreneau, idInscrit,idInscrit_1) values (1,'La peur','Cthulu',2,5,'de6, de8','One shot','Horreur','','Horreur','Lovecraft','Expert','Une petite partie autour du mythe de Cthulu...',1,1,1)");
            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, idCreneau, idInscrit,idInscrit_1) values (2,'Pirates','Pathfinder',3,5,'de6, 12, 20','Campagne ferme','Aventure','...','Leger','Medieval-Fantastique','Confirme, Expert','Des pirates égaré en mer',3,2,null)");
            stmt.executeUpdate("INSERT INTO partie(idPartie, nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, idCreneau, idInscrit,idInscrit_1) values (3,'Mon petit poney','Mon petit poney',4,8,'de6, 12, 20','One shot','Humour','...','Leger','Fantastique','Debutant, Confirme, Expert','Pour rigoler entre amis',2,2,null)");

            stmt.executeUpdate("UPDATE creneau SET idPartie=1 WHERE idCreneau=1");
            stmt.executeUpdate("UPDATE creneau SET idPartie=3 WHERE idCreneau=2");
            stmt.executeUpdate("UPDATE creneau SET idPartie=2 WHERE idCreneau=3");
        }
    }


    @Test
    public void shouldListPartieValide() {
        List<Partie> parties = partieDao.listPartieValide();

        assertThat(parties).hasSize(1);
        assertThat(parties).extracting("idPartie", "nomScenario", "nomJeu", "creneau.idCreneau", "creneau.date", "creneau.heure", "creneau.lieu", "inscrit.idInscrit", "inscrit.nom", "inscrit.prenom").containsOnly(
                tuple(1,"La peur","Cthulu",1,"2012-05-12", "18h-20h", "bar tandem",1,"Dylan","Bob")
        );
    }

    @Test
    public void shouldListPartieEnAttente() {
        List<Partie> parties = partieDao.listPartieEnAttente();

        assertThat(parties).hasSize(2);
        assertThat(parties).extracting("idPartie", "nomScenario", "nomJeu", "creneau.idCreneau", "creneau.date", "creneau.heure", "creneau.lieu", "inscrit.idInscrit", "inscrit.nom", "inscrit.prenom").containsOnly(
                tuple(3, "Mon petit poney", "Mon petit poney", 2, "2015-08-12", "15h-20h", "maison des associations", 2, "Dujardin", "Jean-François"),
                tuple(2, "Pirates", "Pathfinder", 3, "2011-09-12", "18h-22h", "chez Robert", 2, "Dujardin", "Jean-François")
        );
    }

    @Test
    public void shouldGetPartie(){
        Partie partie = partieDao.getPartie(2);

        assertThat(partie).isNotNull();
        assertThat(partie.getIdPartie()).isEqualTo(2);
        assertThat(partie.getNomScenario()).isEqualTo("Pirates");
        assertThat(partie.getNomJeu()).isEqualTo("Pathfinder");
        assertThat(partie.getNbMin()).isEqualTo(3);
        assertThat(partie.getNbMax()).isEqualTo(5);
        assertThat(partie.getDesUtil()).isEqualTo("de6, 12, 20");
        assertThat(partie.getTypeSoiree()).isEqualTo("Campagne ferme");
        assertThat(partie.getGenre()).isEqualTo("Aventure");
        assertThat(partie.getType()).isEqualTo("...");
        assertThat(partie.getTon()).isEqualTo("Leger");
        assertThat(partie.getInspiration()).isEqualTo("Medieval-Fantastique");
        assertThat(partie.getNiveauAttendu()).isEqualTo("Confirme, Expert");
        assertThat(partie.getPresentation()).isEqualTo("Des pirates égaré en mer");
        assertThat(partie.getCreneau().getIdCreneau()).isEqualTo(3);
        assertThat(partie.getCreneau().getDate()).isEqualTo("2011-09-12");
        assertThat(partie.getCreneau().getHeure()).isEqualTo("18h-22h");
        assertThat(partie.getCreneau().getLieu()).isEqualTo("chez Robert");
        assertThat(partie.getCreneau().getInscrit().getIdInscrit()).isEqualTo(1);
        assertThat(partie.getCreneau().getInscrit().getNom()).isEqualTo("Dylan");
        assertThat(partie.getCreneau().getInscrit().getPrenom()).isEqualTo("Bob");
        assertThat(partie.getInscrit().getIdInscrit()).isEqualTo(2);
        assertThat(partie.getInscrit().getNom()).isEqualTo("Dujardin");
        assertThat(partie.getInscrit().getPrenom()).isEqualTo("Jean-François");

    }

    @Test
    public void shouldAjouterPartie() throws SQLException {
        Partie partie = new Partie(4,"nomScenario","nomJeu",1,3,"desUtil","One shot","genre","typeJ","ton","inspiration","niveauAttendu","presentation",new Creneau(4),new Inscrit(1));
        partieDao.ajouterPartie(partie,null);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM partie WHERE nomScenario='nomScenario'");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idCreneau")).isGreaterThan(0);
        assertThat(rs.getString("nomScenario")).isEqualTo("nomScenario");
        assertThat(rs.getString("nomJeu")).isEqualTo("nomJeu");
        assertThat(rs.getInt("nombreMin")).isEqualTo(1);
        assertThat(rs.getInt("nombreMax")).isEqualTo(3);
        assertThat(rs.getString("desUtil")).isEqualTo("desUtil");
        assertThat(rs.getString("typeSoiree")).isEqualTo("One shot");
        assertThat(rs.getString("genre")).isEqualTo("genre");
        assertThat(rs.getString("typeJ")).isEqualTo("typeJ");
        assertThat(rs.getString("ton")).isEqualTo("ton");
        assertThat(rs.getString("inspiration")).isEqualTo("inspiration");
        assertThat(rs.getString("niveauAttendu")).isEqualTo("niveauAttendu");
        assertThat(rs.getString("presentation")).isEqualTo("presentation");
        assertThat(rs.getInt("idCreneau")).isEqualTo(4);
        assertThat(rs.getInt("idInscrit")).isEqualTo(1);
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldValiderPartie() throws SQLException {
        Partie partie = new Partie(1,"nomScenariobis","nomJeu",1,3,"desUtil","One shot","genre","typeJ","ton","inspiration","niveauAttendu","presentation",new Creneau(1),new Inscrit(1));
        partieDao.validerPartie(partie,null,1);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM partie WHERE nomScenario='nomScenariobis'");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idCreneau")).isEqualTo(1);
        assertThat(rs.getString("nomScenario")).isEqualTo("nomScenariobis");
        assertThat(rs.getString("nomJeu")).isEqualTo("nomJeu");
        assertThat(rs.getInt("nombreMin")).isEqualTo(1);
        assertThat(rs.getInt("nombreMax")).isEqualTo(3);
        assertThat(rs.getString("desUtil")).isEqualTo("desUtil");
        assertThat(rs.getString("typeSoiree")).isEqualTo("One shot");
        assertThat(rs.getString("genre")).isEqualTo("genre");
        assertThat(rs.getString("typeJ")).isEqualTo("typeJ");
        assertThat(rs.getString("ton")).isEqualTo("ton");
        assertThat(rs.getString("inspiration")).isEqualTo("inspiration");
        assertThat(rs.getString("niveauAttendu")).isEqualTo("niveauAttendu");
        assertThat(rs.getString("presentation")).isEqualTo("presentation");
        assertThat(rs.getInt("idCreneau")).isEqualTo(1);
        assertThat(rs.getInt("idInscrit")).isEqualTo(1);
        assertThat(rs.getInt("idInscrit_1")).isEqualTo(1);
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldSupprimerPartie() throws SQLException {
        partieDao.supprimerPartie(2,3);
        Partie partie = partieDao.getPartie(2);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM creneau WHERE idCreneau=3");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idPartie")).isEqualTo(0);
        assertThat(rs.next()).isFalse();

        assertThat(partie).isNull();
    }


}
