package daosTests;

import fr.ck.daos.DataSourceProvider;
import fr.ck.daos.PartieDao;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PartieDaoTests {

    private PartieDao partieDao = new PartieDao();

    @Before
    public void initDb() throws Exception{
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM perso");
            stmt.executeUpdate("DELETE FROM jeu");
            stmt.executeUpdate("INSERT INTO jeu(jeu_id,titre,date_sortie,console,imagej,descriptionj) VALUES (1,'Awakening',2012,'Nitendo 3DS','fire-emblem-awakening.jpg','Cet épisode ...')");
            stmt.executeUpdate("INSERT INTO jeu(jeu_id,titre,date_sortie,console,imagej,descriptionj) VALUES (2,'Blinding Blade',2003,'Game Boy Advance','FireEmblem.jpg','Cet épisode ...')");
            stmt.executeUpdate("INSERT INTO perso(perso_id,nom,jeu_id,classe,image,descriptionp) VALUES (1,'Lucina',1,'Epéiste','ChromLucina.png','Fille de Chrom...')");
            stmt.executeUpdate("INSERT INTO perso(perso_id,nom,jeu_id,classe,image,descriptionp) VALUES (2,'Chrom',1,'Epéiste','ChromLucina.png','Prince d\\'Ylisse et porteur de l\\'épée légendaire')");
            stmt.executeUpdate("INSERT INTO perso(perso_id,nom,jeu_id,classe,image,descriptionp) VALUES (3,'Hector',2,'Hachiste','','Frère du marquis d\\'Ositia, il accompagne Eliwood dans la recherche de son père')");
        }
    }

    /*@Test
    public void shouldListPersos(){
        List<PartieDao> personnages = partieDao.listPartie();

        assertThat(personnages).hasSize(3);
        assertThat(personnages).extracting("id","nom","jeuOrigine","classeBase","description").containsOnly(
                tuple(1,"Lucina","Awakening","Epéiste","Fille de Chrom...")	,
                tuple(2,"Chrom","Awakening","Epéiste","Prince d'Ylisse et porteur de l'épée légendaire"),
                tuple(3,"Hector","Blinding Blade","Hachiste","Frère du marquis d'Ositia, il accompagne Eliwood dans la recherche de son père")
        );
    }

    @Test
    public void shouldListClasse() {
        List<String> classes = persoDao.listClasse();

        assertThat(classes).hasSize(2);
        assertThat(classes.get(0)).isEqualTo("Epéiste");
        assertThat(classes.get(1)).isEqualTo("Hachiste");
    }

    @Test
    public void shouldGetPerso() {
        Personnage personnage = persoDao.getPerso(3);

        assertThat(personnage).isNotNull();
        assertThat(personnage.getId()).isEqualTo(3);
        assertThat(personnage.getNom()).isEqualTo("Hector");
        assertThat(personnage.getJeuOrigine()).isEqualTo("Blinding Blade");
        assertThat(personnage.getClasseBase()).isEqualTo("Hachiste");
        assertThat(personnage.getDescription()).isEqualTo("Frère du marquis d'Ositia, il accompagne Eliwood dans la recherche de son père");
    }

    @Test
    public void shouldGetCheminImage() {
        String cheminImage = persoDao.getCheminImage(2);

        assertThat(cheminImage).isNotNull();
        assertThat(cheminImage).isEqualTo("ChromLucina.png");
    }

    @Test
    public void shouldListRecherche() {
        List<Personnage> personnages = persoDao.listRecherche("Lucina");

        assertThat(personnages).hasSize(1);
        assertThat(personnages).extracting("id","nom","jeuOrigine","classeBase","description").containsOnly(
                tuple(1,"Lucina","Awakening","Epéiste","Fille de Chrom...")
        );

    }

    @Test
    public void shouldAjouter() {
        Personnage personnage = new Personnage(4,"Roy","Blazing Blade","Epéiste","Fils du marquis de Pherae Eliwood, il part combattre l\'ennemi Biran, un empire belliqueux");

        persoDao.ajouter(personnage,3,null);

        assertThat(personnage).isNotNull();
        assertThat(personnage.getId()).isEqualTo(4);
        assertThat(personnage.getNom()).isEqualTo("Roy");
        assertThat(personnage.getJeuOrigine()).isEqualTo("Blazing Blade");
        assertThat(personnage.getClasseBase()).isEqualTo("Epéiste");
        assertThat(personnage.getDescription()).isEqualTo("Fils du marquis de Pherae Eliwood, il part combattre l\'ennemi Biran, un empire belliqueux");
    }

    @Test
    public void shouldMettreAJour() {
        Personnage personnage = new Personnage(1,"Lucina","Awakening","Epéiste","Blablabla");

        persoDao.mettreAJour(personnage,1,"LucinaChrom.jpg");
        Personnage perso = persoDao.getPerso(1);

        assertThat(perso).isNotNull();
        assertThat(perso.getId()).isEqualTo(1);
        assertThat(perso.getNom()).isEqualTo("Lucina");
        assertThat(perso.getJeuOrigine()).isEqualTo("Awakening");
        assertThat(perso.getClasseBase()).isEqualTo("Epéiste");
        assertThat(perso.getDescription()).isEqualTo("Blablabla");
    }

    @Test
    public void shouldSupprimerPerso() {
        persoDao.supprimerPerso(1);
        Personnage personnage = persoDao.getPerso(1);
        assertThat(personnage).isNull();
    }*/


}
