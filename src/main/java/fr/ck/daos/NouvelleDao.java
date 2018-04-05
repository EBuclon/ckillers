package fr.ck.daos;

import fr.ck.entite.Nouvelle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NouvelleDao {

    public void ajouterNouvelle(Nouvelle nouvelle){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Nouvelle(titreNouvelle, texte, idInscrit) VALUES ( ?, ?, ?)")) {
            statement.setString(1, nouvelle.getTitre());
            statement.setString(2, nouvelle.getTexte());
            statement.setInt(3, nouvelle.getIdInscrit());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de la nouvelle à la base", e);
        }
    }
}
