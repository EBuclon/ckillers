package fr.ck.daos;

import fr.ck.entite.Inscrit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipationDao {
    public void participer(Integer idPartie, Integer idInscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Participer(idInscrit,idPartie) VALUES (?,?)")){
            statement.setInt(1,idInscrit);
            statement.setInt(2,idPartie);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de l'insertion de la participation", e);
        }
    }

    public void annulerParticiper(Integer idPartie, Integer idInscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Participer WHERE idInscrit=? AND idPartie=?")){
            statement.setInt(1,idInscrit);
            statement.setInt(2,idPartie);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la participation", e);
        }
    }

    public List<Inscrit> listeParticipants(Integer idPartie) {
        List<Inscrit> listeInscrit = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT I.idInscrit,nom,prenom,mail FROM inscrit AS I INNER JOIN participer WHERE I.idInscrit=participer.idInscrit AND idPartie=?"))
        {
            statement.setInt(1,idPartie);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listeInscrit.add(new Inscrit(resultSet.getInt("idInscrit"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("mail")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listeInscrit;
    }
}
