package daosTests;

import fr.ck.daos.CreneauDao;
import fr.ck.daos.DataSourceProvider;
import fr.ck.entite.Creneau;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class CreneauDaoTests {

    private CreneauDao creneauDao = new CreneauDao();

    @Before
    public void initDb() throws Exception{
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM jeu");
            stmt.executeUpdate("INSERT INTO jeu(jeu_id,titre,date_sortie,console,imagej,descriptionj) VALUES (1,'Awakening',2012,'Nitendo 3DS','fire-emblem-awakening.jpg','Cet épisode ...')");
            stmt.executeUpdate("INSERT INTO jeu(jeu_id,titre,date_sortie,console,imagej,descriptionj) VALUES (2,'Blinding Blade',2003,'Game Boy Advance','FireEmblem.jpg','Cet épisode ...')");
        }
    }


}

