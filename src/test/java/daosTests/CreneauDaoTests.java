package daosTests;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.InscritDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class CreneauDaoTests extends GenericDaoTest {

    private InscritDao inscritDao= new InscritDao();
    private CreneauDao creneauDao = new CreneauDao();

    @Test
    public void shouldCreneauxLibres() {
        List<Creneau> creneau = creneauDao.listCreneauxLibres();

        assertThat(creneau).hasSize(1);
        assertThat(creneau).extracting("idCreneau", "date","heure","lieu").containsOnly(Assertions.tuple(4,"2018-09-12","18h-22h","MDA"));
    }

     @Test
    public void shouldListeDateAvecPartie() {
        List<Integer> jours = creneauDao.listeDateAvecPartie("5","2018");

        assertThat(jours).hasSize(1);
        assertThat(jours).containsOnly(12);
    }

    @Test
    public void shouldListePartiesEvenements(){
        List<Partie> partiesEvenements = creneauDao.listPartiesEvenements();

        assertThat(partiesEvenements).hasSize(2);
        assertThat(partiesEvenements).extracting("idPartie", "nomScenario", "nomJeu", "creneau.date", "creneau.heure", "creneau.lieu").containsOnly(
                tuple(1,"La peur","Cthulu","2018-05-12", "18h-20h", "bar tandem"),
                tuple(1,"Open JDR","","2018-10-25","14h-22h","bar tandem")
        );
    }

    @Test
    public void shouldGetCreneau() {
        Creneau creneau1 = creneauDao.getCreneau(1);

        assertThat(creneau1).isNotNull();
        assertThat(creneau1.getIdCreneau()).isEqualTo(1);
        assertThat(creneau1.getDate()).isEqualTo("2018-05-12");
        assertThat(creneau1.getHeure()).isEqualTo("18h-20h");
        assertThat(creneau1.getLieu()).isEqualTo("bar tandem");
        assertThat(creneau1.getInscrit().getIdInscrit()).isEqualTo(1);
        assertThat(creneau1.getInscrit().getNom()).isEqualTo("Dylan");
        assertThat(creneau1.getInscrit().getPrenom()).isEqualTo("Bob");
    }

    @Test
    public void shouldAjouterCreneau() throws Exception {
        Inscrit inscrit1 = inscritDao.getInscritParMail("bob@dylan.us");
        Creneau creneau1 = new Creneau(4,"2018-01-23","18h-20h","Chez moi",inscrit1);

        creneauDao.ajouterCreneau(creneau1);

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM creneau WHERE lieu='Chez moi'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("idCreneau")).isGreaterThan(0);
                assertThat(rs.getString("dateCreneau")).isEqualTo("2018-01-23");
                assertThat(rs.getString("heure")).isEqualTo("18h-20h");
                assertThat(rs.getString("lieu")).isEqualTo("Chez moi");
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldSupprimerCreneau(){
        creneauDao.supprimerCreneau(4);
        Creneau creneau = creneauDao.getCreneau(4);
        assertThat(creneau).isNull();
    }

}




