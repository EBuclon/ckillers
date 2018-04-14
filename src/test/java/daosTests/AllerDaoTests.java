package daosTests;

import fr.ck.daos.AllerDao;
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

public class AllerDaoTests extends GenericDaoTest {

    private AllerDao allerDao = new AllerDao();

    @Test
    public void shouldListAllants() {
        List<Inscrit> inscrits = allerDao.listeAllants(1);

        assertThat(inscrits).hasSize(1);
        assertThat(inscrits).extracting("idInscrit", "nom", "prenom", "mail").containsOnly(
                Assertions.tuple(1,"Dylan", "Bob", "bob@dylan.us")
        );
    }

    @Test
    public void shouldAller() throws SQLException {
        allerDao.aller(1,2);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aller WHERE idEvenement=1 AND idInscrit=2");
        assertThat(rs.next()).isTrue();
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldAnnulerAller() throws SQLException {
        allerDao.annulerAller(1,1);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aller WHERE idEvenement=1 AND idInscrit=1");
        assertThat(rs.next()).isFalse();
    }
}
