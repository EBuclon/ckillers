package fr.ck.daos;

import fr.ck.entite.Inscrit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllerDao {
    /**
     * Fonction de participation à un évènement
     * @param idEvenement
     * @param idInscrit
     */
    public void aller(Integer idEvenement, Integer idInscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Aller(idEvenement,idInscrit) VALUES (?,?)")){
            statement.setInt(1,idEvenement);
            statement.setInt(2,idInscrit);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de l'insertion de la participation", e);
        }
    }

    /**
     * Fonction d'annulation de la participation à un évènement
     * @param idEvenement
     * @param idInscrit
     */
    public void annulerAller(Integer idEvenement, Integer idInscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Aller WHERE idInscrit=? AND idEvenement=?")){
            statement.setInt(1,idInscrit);
            statement.setInt(2,idEvenement);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la participation", e);
        }
    }

    /**
     * Liste les participants à un évènement
     * @param idEvenement
     * @return
     */
    public List<Inscrit> listeAllants(Integer idEvenement) {
        List<Inscrit> listeInscrit = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT I.idInscrit,nom,prenom,mail FROM inscrit AS I INNER JOIN aller WHERE I.idInscrit=aller.idInscrit AND idEvenement=?"))
        {
            statement.setInt(1,idEvenement);
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
