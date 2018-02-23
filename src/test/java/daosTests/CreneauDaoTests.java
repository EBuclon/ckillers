package daosTests;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.InscritDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CreneauDaoTests {

    private InscritDao inscritDao= new InscritDao();
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
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idEvenement,idPartie,idInscrit) VALUES (1,'2012-05-12','18h-20h','bar tandem',null,null,1)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idEvenement,idPartie,idInscrit) VALUES (2,'2015-08-12','15h-20h','maison des associations',null,null,null)");
            stmt.executeUpdate("INSERT INTO creneau(idCreneau,dateCreneau,heure,lieu,idEvenement,idPartie,idInscrit) VALUES (3,'2011-09-12','18h-22h','chez Robert',null,null,null)");
        }
    }

    @Test
    public void shouldCreneauxLibres() {
        List<Creneau> creneau = creneauDao.listCreneauxLibres();

        assertThat(creneau).hasSize(3);
        assertThat(creneau).extracting("idCreneau", "date","heure","lieu").containsOnly(Assertions.tuple(1,"2012-05-12","18h-20h","bar tandem"), Assertions.tuple(2,"2015-08-12","15h-20h","maison des associations"),
                Assertions.tuple(3,"2011-09-12","18h-22h","chez Robert"));
    }

    @Test
    public void shouldGetCreneau() {
        Creneau creneau1 = creneauDao.getCreneau(1);

        assertThat(creneau1).isNotNull();
        assertThat(creneau1.getIdCreneau()).isEqualTo(1);
        assertThat(creneau1.getDate()).isEqualTo("2012-05-12");
        assertThat(creneau1.getHeure()).isEqualTo("18h-20h");
        assertThat(creneau1.getLieu()).isEqualTo("bar tandem");
        assertThat(creneau1.getInscrit().getIdInscrit()).isEqualTo(1);
        assertThat(creneau1.getInscrit().getNom()).isEqualTo("Dylan");
        assertThat(creneau1.getInscrit().getPrenom()).isEqualTo("Bob");
    }




    @Test
    public void shouldAddCreneau() throws Exception {
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
    public void shouldDeleteCreneau() throws Exception{
        creneauDao.supprimerCreneau(2);
        Creneau creneau = creneauDao.getCreneau(2);
        assertThat(creneau).isNull();
    }

}




