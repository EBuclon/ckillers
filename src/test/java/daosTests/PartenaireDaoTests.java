package daosTests;

import fr.ck.daos.DataSourceProvider;
import fr.ck.daos.PartenaireDao;
import fr.ck.entite.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PartenaireDaoTests extends GenericDaoTest {

    private PartenaireDao partenaireDao = new PartenaireDao();

    @Test
    public void shouldListPartenaires() {
        List<Partenaire> partenaires = partenaireDao.listPartenaires();

        assertThat(partenaires).hasSize(1);
        assertThat(partenaires).extracting("idPartenaire", "nom","description","Inscrit.idInscrit","Inscrit.nom","Inscrit.prenom").containsOnly(
                Assertions.tuple(1,"Le Bazar du Bizarre","Marchant de jeux en tout genres",1,"Dylan","Bob")
        );
    }

    @Test
    public void shouldAjouterPartenaire() throws SQLException {
        Partenaire partenaire = new Partenaire(2,"HAHA","LALALALLALALA",new Inscrit(1));
        partenaireDao.ajouterPartenaire(partenaire);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM partenaire WHERE nomPartenaire='HAHA'");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idPartenaire")).isGreaterThan(0);
        assertThat(rs.getString("nomPartenaire")).isEqualTo("HAHA");
        assertThat(rs.getString("descriptionPartenaire")).isEqualTo("LALALALLALALA");
        assertThat(rs.getInt("idInscrit")).isEqualTo(1);
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldGetImagePartenaire(){
        String image = partenaireDao.getImagePartenaire(1);
        assertThat(image).isEqualTo("imagePartenaire");
    }

    @Test
    public void shouldSupprimerEvenement() throws SQLException {
        partenaireDao.supprimerPartenaire(1);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM partenaire WHERE idPartenaire=1");
        assertThat(rs.next()).isFalse();
    }

}
