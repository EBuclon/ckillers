package fr.ck.daos;

import fr.ck.entite.Creneau;
import fr.ck.entite.Partie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartieDao {

    public List<Partie> listParties() {
        List<Partie> parties = new ArrayList<Partie>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Partie INNER JOIN Creneau WHERE Partie.idCreneau=Creneau.idCreneau")) {
            while (resultSet.next()) {
                parties.add(
                        new Partie(
                                resultSet.getInt("idPartie"),
                                resultSet.getString("nomScenario"),
                                resultSet.getString("nomJeu"),
                                resultSet.getInt("nbMin"),
                                resultSet.getInt("nbMax"),
                                resultSet.getString("deUtil"),
                                resultSet.getString("typeSoiree"),
                                resultSet.getString("genre"),
                                resultSet.getString("type"),
                                resultSet.getString("ton"),
                                resultSet.getString("inspiration"),
                                resultSet.getString("niveauAttendu"),
                                resultSet.getString("presentation"),
                                new Creneau(resultSet.getInt("idCreneau"),
                                        resultSet.getString("date"),
                                        resultSet.getString("heure"),
                                        resultSet.getString("lieu"),
                                        resultSet.getInt("creneau.idInscrit")
                                        ),
                                resultSet.getInt("idInscrit"),
                                resultSet.getInt("idInscrit2")
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des parties", e);
        }
        return parties;
    }

    public void ajouterPartie(Partie partie){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Partie(nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, idCreneau, idInscrit) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?)")) {
            statement.setString(1, partie.getNomScenario());
            statement.setString(2, partie.getNomJeu());
            statement.setInt(3, partie.getNbMin());
            statement.setInt(4, partie.getNbMax());
            statement.setString(5, partie.getDesUtil());
            statement.setString(6, partie.getTypeSoiree());
            statement.setString(7, partie.getGenre());
            statement.setString(8, partie.getType());
            statement.setString(9, partie.getTon());
            statement.setString(10, partie.getInspiration());
            statement.setString(11, partie.getNiveauAttendu());
            statement.setString(12, partie.getPresentation());
            statement.setInt(13, partie.getCreneau().getIdCreneau());
            statement.setInt(14, partie.getIdInscrit());
            /*if (image != null) {
                statement.setString(4, image);
            } else {
                statement.setString(4, );
            }*/
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de la partie dans la base", e);
        }
    }


}
