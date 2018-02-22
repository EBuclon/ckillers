package fr.ck.daos;

import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreneauDao {
    public List<Creneau> listCreneauxLibres() {
        List<Creneau> creneaux = new ArrayList<Creneau>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT idCreneau,dateCreneau,heure,lieu FROM creneau WHERE idPartie is null AND idEvenement is null ORDER BY dateCreneau,heure;\n")) {
            while (resultSet.next()) {
                creneaux.add(
                        new Creneau(
                                resultSet.getInt("idCreneau"),
                                resultSet.getString("dateCreneau"),
                                resultSet.getString("heure"),
                                resultSet.getString("lieu"),
                                new Inscrit()
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des creneaux horraires", e);
        }
        return creneaux;
    }

    public Creneau getCreneau(Integer idCreneau){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT C.idCreneau,dateCreneau,heure,lieu,I.idInscrit,nom,prenom FROM creneau as C INNER JOIN inscrit as I WHERE I.idInscrit=C.idInscrit AND idCreneau=?")) {
            statement.setInt(1, idCreneau);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    return new Creneau(
                            resultSet.getInt("idCreneau"),
                            resultSet.getString("dateCreneau"),
                            resultSet.getString("heure"),
                            resultSet.getString("lieu"),
                            new Inscrit(resultSet.getInt("idInscrit"),
                                    resultSet.getString("nom"),
                                    resultSet.getString("prenom")
                                    )
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement du Creneau", e);
        }
        return null;
    }

    public void ajouterCreneau(Creneau creneau){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Creneau(dateCreneau, heure, lieu, idInscrit) VALUES ( ?, ?, ?, ?)")) {
            statement.setString(1, creneau.getDate());
            statement.setString(2, creneau.getHeure());
            statement.setString(3, creneau.getLieu());
            statement.setInt(4, creneau.getIncrit().getIdInscrit());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion du jeu à la base", e);
        }
    }

    public void supprimerCreneau(Integer id){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Creneau WHERE idCreneau=?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Erreur lors de la suppression du creneau");
        }
    }
}
