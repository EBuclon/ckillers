package fr.ck.daos;

import fr.ck.entite.Creneau;
import fr.ck.entite.Evenement;
import fr.ck.entite.Inscrit;

import java.sql.*;

public class EvenementDao {
    /**
     *
     * @param evenement
     */
    public void ajouterEvent(Evenement evenement){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Evenement(titre, contenu) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, evenement.getTitre());
            statement.setString(2, evenement.getContenu());
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            resultset.next();

            int idEvent = resultset.getInt(1);

            try (PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Creneau(dateCreneau, heure, lieu, idEvenement, idInscrit) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement2.setString(1, evenement.getCreneau().getDate());
                statement2.setString(2, evenement.getCreneau().getHeure());
                statement2.setString(3, evenement.getCreneau().getLieu());
                statement2.setInt(4, idEvent);
                statement2.setInt(5, evenement.getCreneau().getInscrit().getIdInscrit());
                statement2.executeUpdate();
                ResultSet resultset2 = statement2.getGeneratedKeys();
                resultset2.next();

                int idCreneau = resultset2.getInt(1);

                try(PreparedStatement statement3 = connection.prepareStatement("UPDATE Evenement SET idCreneau=? WHERE idEvenement=?")){
                    statement3.setInt(1, idCreneau);
                    statement3.setInt(2, idEvent);
                    statement3.executeUpdate();
                }
            }

        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de l'evenement dans la base", e);
        }
    }

    /**
     *
     * @param idEvenement
     * @return
     */
    public Evenement getEvenement(Integer idEvenement){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT titre, contenu, C.idCreneau, dateCreneau, heure, lieu, I.idInscrit, nom, prenom  " +
                     "FROM Evenement E INNER JOIN Creneau C INNER JOIN Inscrit I " +
                     "WHERE E.idCreneau=C.idCreneau AND C.idInscrit=I.idInscrit AND E.idEvenement=?")) {
            statement.setInt(1, idEvenement);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    return new Evenement(
                            idEvenement,
                            resultSet.getString("titre"),
                            resultSet.getString("contenu"),
                            new Creneau(resultSet.getInt("idCreneau"),
                                    resultSet.getString("dateCreneau"),
                                    resultSet.getString("heure"),
                                    resultSet.getString("lieu"),
                                    new Inscrit(resultSet.getInt("idInscrit"),
                                            resultSet.getString("nom"),
                                            resultSet.getString("prenom"))
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement de l'evenement", e);
        }
        return null;
    }

    /**
     *
     * @param idEvenement
     * @return
     */
    public String getImageEvent(Integer idEvenement) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT imageE FROM evenement WHERE idEvenement=?")) {
            statement.setInt(1, idEvenement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("imageE");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur dans l'obtention du chemin de l'image", e);
        }
        return null;
    }

    /**
     *
     * @param idEvenement
     * @param image
     */
    public void ajouterImageE(Integer idEvenement, String image){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Evenement SET imageE=? WHERE idEvenement=?")) {
            if (image != null) {
                statement.setString(1, image);
            }
            statement.setInt(2, idEvenement);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement de l'image", e);
        }
    }

    /**
     *
     * @param idEvenement
     * @param idCreneau
     */
    public void supprimerEvenement(Integer idEvenement,Integer idCreneau){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("update creneau set idEvenement=null where idCreneau=?;")){
            statement.setInt(1,idCreneau);
            statement.executeUpdate();
            try (PreparedStatement statement2 = connection.prepareStatement("DELETE FROM aller WHERE idEvenement=?")){
                statement2.setInt(1,idEvenement);
                statement2.executeUpdate();
                try (PreparedStatement statement3 = connection.prepareStatement("DELETE from evenement where idEvenement=?;")){
                    statement3.setInt(1,idEvenement);
                    statement3.executeUpdate();
                    try (PreparedStatement statement4 = connection.prepareStatement("DELETE from creneau where idCreneau=?;")){
                        statement4.setInt(1,idCreneau);
                        statement4.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la partie");
        }
    }
}
