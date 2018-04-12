package daosTests;

import fr.ck.daos.ParticipationDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.entite.Inscrit;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipationDaoTests extends GenericDaoTest {

    private ParticipationDao participationDao = new ParticipationDao();

    @Test
    public void shouldListParticipants() {
        List<Inscrit> inscrits = participationDao.listeParticipants(1);

        assertThat(inscrits).hasSize(1);
        assertThat(inscrits).extracting("idInscrit", "nom", "prenom", "mail").containsOnly(
                Assertions.tuple(1,"Dylan", "Bob", "bob@dylan.us")
        );
    }

    @Test
    public void shouldParticiper() throws SQLException {
        participationDao.participer(1,2);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM participer WHERE idPartie=1 AND idInscrit=2");
        assertThat(rs.next()).isTrue();
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldAnnulerParticiper() throws SQLException {
        participationDao.annulerParticiper(1,1);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM participer WHERE idPartie=1 AND idInscrit=1");
        assertThat(rs.next()).isFalse();
    }
}
