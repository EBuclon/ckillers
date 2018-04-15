package fr.ck.daos;

import fr.ck.entite.Inscrit;
import fr.ck.entite.Partenaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartenaireDao {

    /**
     * permet d'ajouter des partenaires à la base de donnée
     * @param partenaire
     */
    public void ajouterPartenaire(Partenaire partenaire){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Partenaire(nomPartenaire, descriptionPartenaire, idInscrit) VALUES ( ?, ?, ?)")) {
            statement.setString(1, partenaire.getNom());
            statement.setString(2, partenaire.getDescription());
            statement.setInt(3, partenaire.getInscrit().getIdInscrit());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion du partenaire à la base", e);
        }
    }

    /**
     * permet de lister tous les partenaires
     * @return
     */
    public List<Partenaire> listPartenaires() {
        List<Partenaire> partenaires = new ArrayList<Partenaire>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT P.*,nom,prenom FROM Partenaire P INNER JOIN inscrit I " +
                     "WHERE P.idInscrit = I.idInscrit")){
            while (resultSet.next()) {
                partenaires.add(
                        new Partenaire(
                                resultSet.getInt("idPartenaire"),
                                resultSet.getString("nomPartenaire"),
                                resultSet.getString("descriptionPartenaire"),
                                new Inscrit(resultSet.getInt("idInscrit"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("prenom"))
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de collecte des partenaires", e);
        }
        return partenaires;
    }

    /**
     * permet de supprimmer un partenaire
     * @param id
     */
    public void supprimerPartenaire(Integer id){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Partenaire WHERE idPartenaire=?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la partenaire");
        }
    }

    /**
     * permet d'obtenir l'image d'un partenaire
     * @param idPartenaire
     * @return
     */
    public String getImagePartenaire(Integer idPartenaire) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT imageP FROM partenaire WHERE idPartenaire=?")) {
            statement.setInt(1, idPartenaire);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("imageP");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur dans l'obtention du chemin de l'image", e);
        }
        return null;
    }
}
