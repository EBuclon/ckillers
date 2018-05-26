package fr.ck.daos;

import fr.ck.entite.Inscrit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipationDao {

    /**
     * permet de participer à une partie
     * @param idPartie
     * @param idInscrit
     */
    public void participer(Integer idPartie, Integer idInscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT nombreMax,COUNT(participer.idInscrit) as compte FROM partie INNER JOIN participer WHERE participer.idPartie=? AND participer.idPartie=partie.idPartie;")){
            statement.setInt(1,idPartie);

            Integer nbMax = 0;
            Integer nbActuel = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    nbMax = resultSet.getInt("nombreMax");
                    nbActuel = resultSet.getInt("compte");
                }
                if(nbActuel<nbMax+1){
                    try(PreparedStatement statement2 = connection.prepareStatement("SELECT statut,nbrPartieJouees FROM inscrit WHERE idInscrit=?")) {
                        statement2.setInt(1, idInscrit);


                        //COmpter nombre parties par count (particiption)



                        Integer nbrPartieJouees = 3;
                        String statut = "inscrit";
                        try (ResultSet resultSet2 = statement2.executeQuery()) {
                            while (resultSet2.next()) {
                                nbrPartieJouees = resultSet2.getInt("nbrPartieJouees");
                                statut = resultSet2.getString("statut");
                            }
                            if (!statut.equals("inscrit") || nbrPartieJouees <= 1) {
                                try(PreparedStatement statement3 = connection.prepareStatement("INSERT INTO Participer(idInscrit,idPartie) VALUES (?,?)")) {
                                    statement3.setInt(1, idInscrit);
                                    statement3.setInt(2, idPartie);
                                    statement3.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de l'insertion de la participation", e);
        }
    }

    /**
     * permet d'annuler sa participation
     * @param idPartie
     * @param idInscrit
     */
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

    /**
     * permet d'obtenir la liste des participants à une partie
     * @param idPartie
     * @return
     */
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
