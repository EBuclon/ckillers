package daosTests;

import fr.ck.daos.DataSourceProvider;
import fr.ck.daos.NouvelleDao;
import fr.ck.entite.Creneau;
import fr.ck.entite.Evenement;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Nouvelle;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NouvelleDaoTests extends GenericDaoTest {

    private NouvelleDao nouvelleDao = new NouvelleDao();

    @Test
    public void shouldListNouvelles() {
        List<Nouvelle> nouvelles = nouvelleDao.listNouvelle();

        assertThat(nouvelles).hasSize(1);
        assertThat(nouvelles).extracting("idNouvelle", "titre","texte","Inscrit.idInscrit","Inscrit.nom","Inscrit.prenom").containsOnly(
                Assertions.tuple(1,"Bonne nouvelle","Site bientot fini!",1,"Dylan","Bob")
        );
    }

    @Test
    public void shouldAjouterNouvelle() throws SQLException {
        Nouvelle nouvelle = new Nouvelle(2,"Bonjour","Nouvelle nouvelle",new Inscrit(1));
        nouvelleDao.ajouterNouvelle(nouvelle);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM nouvelle WHERE titreNouvelle='Bonjour'");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getInt("idNouvelle")).isGreaterThan(0);
        assertThat(rs.getString("titreNouvelle")).isEqualTo("Bonjour");
        assertThat(rs.getString("texte")).isEqualTo("Nouvelle nouvelle");
        assertThat(rs.getInt("idInscrit")).isEqualTo(1);
        assertThat(rs.next()).isFalse();
    }

    @Test
    public void shouldSupprimerEvenement() throws SQLException {
        nouvelleDao.supprimerNouvelle(1);

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM nouvelle WHERE idNouvelle=1");
        assertThat(rs.next()).isFalse();
    }
}
