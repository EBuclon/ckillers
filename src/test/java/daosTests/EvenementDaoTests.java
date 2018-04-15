package daosTests;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.daos.EvenementDao;
import fr.ck.daos.PartieDao;
import fr.ck.entite.Creneau;
import fr.ck.entite.Evenement;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class EvenementDaoTests extends GenericDaoTest {

    private EvenementDao evenementDao = new EvenementDao();
    private CreneauDao creneauDao = new CreneauDao();

    @Test
    public void shouldGetEvenement(){
        Evenement evenement = evenementDao.getEvenement(1);

        assertThat(evenement).isNotNull();
        assertThat(evenement.getId_Event()).isEqualTo(1);
        assertThat(evenement.getTitre()).isEqualTo("Open JDR");
        assertThat(evenement.getContenu()).isEqualTo("Beaucoup de joueurs attendus!");
        assertThat(evenement.getCreneau().getIdCreneau()).isEqualTo(5);
        assertThat(evenement.getCreneau().getDate()).isEqualTo("2018-10-25");
        assertThat(evenement.getCreneau().getHeure()).isEqualTo("14h-22h");
        assertThat(evenement.getCreneau().getLieu()).isEqualTo("bar tandem");
        assertThat(evenement.getCreneau().getInscrit().getIdInscrit()).isEqualTo(1);
        assertThat(evenement.getCreneau().getInscrit().getNom()).isEqualTo("Dylan");
        assertThat(evenement.getCreneau().getInscrit().getPrenom()).isEqualTo("Bob");
    }

    @Test
    public void shouldAjouterEvenement() throws SQLException {
        Evenement evenement = new Evenement("Bonjour","Nouvel evenement",new Creneau(6,"2018-11-14","14h-18h","Quelquepart",new Inscrit(1)));
        evenementDao.ajouterEvent(evenement);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT E.*,C.dateCreneau,C.heure,C.lieu,I.idInscrit,I.nom,I.prenom FROM evenement E LEFT JOIN Creneau C ON E.idCreneau=C.idCreneau LEFT JOIN Inscrit I ON I.idInscrit=C.idInscrit WHERE titre='Bonjour'");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idEvenement")).isGreaterThan(0);
        assertThat(rs.getString("titre")).isEqualTo("Bonjour");
        assertThat(rs.getString("contenu")).isEqualTo("Nouvel evenement");
        assertThat(rs.getInt("idCreneau")).isGreaterThan(0);
        assertThat(rs.getString("dateCreneau")).isEqualTo("2018-11-14");
        assertThat(rs.getString("heure")).isEqualTo("14h-18h");
        assertThat(rs.getString("lieu")).isEqualTo("Quelquepart");
        assertThat(rs.getInt("idInscrit")).isEqualTo(1);
        assertThat(rs.getString("nom")).isEqualTo("Dylan");
        assertThat(rs.getString("prenom")).isEqualTo("Bob");
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldGetImageE(){
        String image = evenementDao.getImageEvent(1);
        assertThat(image).isEqualTo("imageOpenJdr.png");
    }

    @Test
    public void shouldAjouterImageE(){
        evenementDao.ajouterImageE(1,"nouvelleImage");

        String image = evenementDao.getImageEvent(1);
        assertThat(image).isEqualTo("nouvelleImage");

    }

    @Test
    public void shouldSupprimerEvenement() throws SQLException {
        evenementDao.supprimerEvenement(1,5);
        Evenement evenement = evenementDao.getEvenement(1);
        Creneau creneau = creneauDao.getCreneau(5);

        assertThat(evenement).isNull();
        assertThat(creneau).isNull();
    }
}
