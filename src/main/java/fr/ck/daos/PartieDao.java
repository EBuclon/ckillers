package fr.ck.daos;

import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartieDao {

    public List<Partie> listPartieValide() {
        List<Partie> parties = new ArrayList<Partie>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT P.idPartie, nomScenario, nomJeu, C.idCreneau, dateCreneau, heure, lieu, I.idInscrit, nom, prenom\n" +
                     "FROM Partie P INNER JOIN Creneau C INNER JOIN Inscrit I \n" +
                     "WHERE P.idCreneau=C.idCreneau AND idInscrit_1 IS NOT NULL AND P.idInscrit=I.idInscrit")) {
            while (resultSet.next()) {
                parties.add(
                        new Partie(
                                resultSet.getInt("idPartie"),
                                resultSet.getString("nomScenario"),
                                resultSet.getString("nomJeu"),
                                new Creneau(resultSet.getInt("idCreneau"),
                                        resultSet.getString("dateCreneau"),
                                        resultSet.getString("heure"),
                                        resultSet.getString("lieu"),
                                        new Inscrit()
                                ),
                                new Inscrit(resultSet.getInt("idInscrit"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("prenom"))
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des parties", e);
        }
        return parties;
    }

    public List<Partie> listPartieEnAttente() {
        List<Partie> parties = new ArrayList<Partie>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT P.idPartie, nomScenario, nomJeu, C.idCreneau, dateCreneau, heure, lieu, I.idInscrit, nom, prenom\n" +
                     "FROM Partie P INNER JOIN Creneau C INNER JOIN Inscrit I \n" +
                     "WHERE P.idCreneau=C.idCreneau AND idInscrit_1 IS NULL AND P.idInscrit=I.idInscrit")) {
            while (resultSet.next()) {
                parties.add(
                        new Partie(
                                resultSet.getInt("idPartie"),
                                resultSet.getString("nomScenario"),
                                resultSet.getString("nomJeu"),
                                new Creneau(resultSet.getInt("idCreneau"),
                                        resultSet.getString("dateCreneau"),
                                        resultSet.getString("heure"),
                                        resultSet.getString("lieu"),
                                        new Inscrit()
                                        ),
                                new Inscrit(resultSet.getInt("idInscrit"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("prenom"))
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des parties", e);
        }
        return parties;
    }

    public void ajouterPartie(Partie partie){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Partie(nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, idCreneau, idInscrit) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
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
            statement.setInt(14, partie.getInscrit().getIdInscrit());
            /*if (image != null) {
                statement.setString(4, image);
            } else {
                statement.setString(4, );
            }*/
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            resultset.next();
            int idPartie = resultset.getInt(1);
                try(PreparedStatement statement2 = connection.prepareStatement("UPDATE creneau SET idPartie=? WHERE idCreneau=?")){
                    statement2.setInt(1, idPartie);
                    statement2.setInt(2, partie.getCreneau().getIdCreneau());
                    statement2.executeUpdate();
                }
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de la partie dans la base", e);
        }
    }

    public void validerPartie(Partie partie, Integer idInscrit){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Partie SET nomScenario=?, nomJeu=?, nombreMin=?, nombreMax=?, desUtil=?, typeSoiree=?, genre=?, typeJ=?, ton=?, inspiration=?, niveauAttendu=?, presentation=?, idInscrit_1=? WHERE idPartie=?")) {
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
            statement.setInt(13, idInscrit);
            statement.setInt(14, partie.getIdPartie());
            /*if (image != null) {
                statement.setString(4, image);
            } else {
                statement.setString(4, );
            }*/
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la validation de la partie dans la base", e);
        }
    }

    public Partie getPartie(Integer idPartie){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT nomScenario, nomJeu, nombreMin, nombreMax, desUtil, typeSoiree, genre, typeJ, ton, inspiration, niveauAttendu, presentation, " +
                     "C.idCreneau, dateCreneau, heure, lieu, I.idInscrit, I.nom, I.prenom, C.idInscrit as idInscritC, Ic.nom as nomC, Ic.prenom as prenomC " +
                     "FROM Partie P INNER JOIN Creneau C INNER JOIN Inscrit I INNER JOIN Inscrit Ic " +
                     "WHERE P.idCreneau=C.idCreneau AND P.idInscrit=I.idInscrit AND Ic.idInscrit=C.idInscrit AND P.idPartie=?")) {
            statement.setInt(1, idPartie);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    return new Partie(
                            idPartie,
                            resultSet.getString("nomScenario"),
                            resultSet.getString("nomJeu"),
                            resultSet.getInt("nombreMin"),
                            resultSet.getInt("nombreMax"),
                            resultSet.getString("desUtil"),
                            resultSet.getString("typeSoiree"),
                            resultSet.getString("genre"),
                            resultSet.getString("typeJ"),
                            resultSet.getString("ton"),
                            resultSet.getString("inspiration"),
                            resultSet.getString("niveauAttendu"),
                            resultSet.getString("presentation"),
                            new Creneau(resultSet.getInt("idCreneau"),
                                    resultSet.getString("dateCreneau"),
                                    resultSet.getString("heure"),
                                    resultSet.getString("lieu"),
                                    new Inscrit(resultSet.getInt("idInscritC"),
                                            resultSet.getString("nomC"),
                                            resultSet.getString("prenomC"))
                            ),
                            new Inscrit(resultSet.getInt("idInscrit"),
                                    resultSet.getString("nom"),
                                    resultSet.getString("prenom"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement de la partie", e);
        }
        return null;
    }

    public void supprimerPartie(Integer idPartie,Integer idCreneau){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("update creneau set idPartie=null where idCreneau=?;")){
            statement.setInt(1,idCreneau);
            statement.executeUpdate();
            try (PreparedStatement statement2 = connection.prepareStatement("DELETE FROM participer WHERE idPartie=?")){
                statement2.setInt(1,idPartie);
                statement2.executeUpdate();
                try (PreparedStatement statement3 = connection.prepareStatement("DELETE from partie where idPartie=?;")){
                    statement3.setInt(1,idPartie);
                    statement3.executeUpdate();
                }
                }
        }
        catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la partie");
        }
    }
}
