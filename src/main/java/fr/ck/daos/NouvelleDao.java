package fr.ck.daos;

import fr.ck.entite.Inscrit;
import fr.ck.entite.Nouvelle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NouvelleDao {

    public void ajouterNouvelle(Nouvelle nouvelle){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Nouvelle(titreNouvelle, texte, idInscrit) VALUES ( ?, ?, ?)")) {
            statement.setString(1, nouvelle.getTitre());
            statement.setString(2, nouvelle.getTexte());
            statement.setInt(3, nouvelle.getInscrit().getIdInscrit());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de la nouvelle à la base", e);
        }
    }

    public List<Nouvelle> listNouvelle() {
        List<Nouvelle> nouvelles = new ArrayList<Nouvelle>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT N.*,nom,prenom FROM Nouvelle N INNER JOIN inscrit I " +
                     "WHERE N.idInscrit = I.idInscrit")){
            while (resultSet.next()) {
                nouvelles.add(
                        new Nouvelle(
                                resultSet.getInt("idNouvelle"),
                                resultSet.getString("titreNouvelle"),
                                resultSet.getString("texte"),
                                new Inscrit(resultSet.getInt("idInscrit"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("prenom"))
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des nouvelles", e);
        }
        return nouvelles;
    }

    public void supprimerNouvelle(Integer id){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Nouvelle WHERE idNouvelle=?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression de la nouvelle");
        }
    }
}
