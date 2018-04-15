package fr.ck.daos;

import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreneauDao {
    /**
     * Liste les créneaux disponible aux inscrits pour créer des parties
     * @return
     */
    public List<Creneau> listCreneauxLibres() {
        List<Creneau> creneaux = new ArrayList<Creneau>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT idCreneau,dateCreneau,heure,lieu FROM creneau WHERE idPartie is null AND idEvenement is null ORDER BY dateCreneau,heure ")) {
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

    /**
     * Liste les jours d'un mois avec des parties pour le calendrier
     * @param mois
     * @param annee
     * @return
     */
    public List<Integer> listeDateAvecPartie(String mois, String annee) {
        List<Integer> jours = new ArrayList<Integer>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT dateCreneau FROM creneau C INNER JOIN partie P WHERE P.idPartie=C.idPartie AND idInscrit_1 is not null ORDER BY dateCreneau,heure ")) {
            while (resultSet.next()) {
                String[] parts = resultSet.getString("dateCreneau").split("-");
                if(parts[0].equals(annee) && ( parts[1].equals(mois) || parts[1].equals("0"+mois) ) ) {
                    jours.add(
                            Integer.parseInt(parts[2])
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des dates", e);
        }
        return jours;
    }

    /**
     * Liste les parties et évènements dans l'ordre chronologique pour la page d'accueil (les evenements sont traités comme des parties)
     * @return
     */
    public List<Partie> listPartiesEvenements() {
        List<Partie> parties = new ArrayList<Partie>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT C.idCreneau, dateCreneau, heure, lieu, P.idPartie, nomScenario, nomJeu, E.idEvenement, titre " +
                     "FROM Partie P RIGHT JOIN Creneau C ON P.idCreneau=C.idCreneau LEFT JOIN Evenement E ON C.idCreneau=E.idCreneau " +
                     "WHERE P.idInscrit_1 IS NOT NULL OR titre IS NOT NULL ORDER BY dateCreneau DESC, heure DESC")) {
            while (resultSet.next()) {
                if (resultSet.getString("nomScenario")!=null) {
                    parties.add(
                            new Partie(
                                    resultSet.getInt("idPartie"),
                                    resultSet.getString("nomScenario"),
                                    resultSet.getString("nomJeu"),
                                    new Creneau(resultSet.getString("dateCreneau"),
                                            resultSet.getString("heure"),
                                            resultSet.getString("lieu")
                                    )
                            ));
                }else{
                    parties.add(
                            new Partie(
                                    resultSet.getInt("idEvenement"),
                                    resultSet.getString("titre"),
                                    "",
                                    new Creneau(resultSet.getString("dateCreneau"),
                                            resultSet.getString("heure"),
                                            resultSet.getString("lieu")
                                    )
                            ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de Collecte des parties", e);
        }
        return parties;
    }

    /**
     * Obtenir les informations liées à un créneau
     * @param idCreneau
     * @return
     */
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

    /**
     * Pour ajouter un créneau horraire
     * @param creneau
     */
    public void ajouterCreneau(Creneau creneau){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Creneau(dateCreneau, heure, lieu, idInscrit) VALUES ( ?, ?, ?, ?)")) {
            statement.setString(1, creneau.getDate());
            statement.setString(2, creneau.getHeure());
            statement.setString(3, creneau.getLieu());
            statement.setInt(4, creneau.getInscrit().getIdInscrit());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion du creneau à la base", e);
        }
    }

    /**
     * Pour supprimer un créneau
     * @param id
     */
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
