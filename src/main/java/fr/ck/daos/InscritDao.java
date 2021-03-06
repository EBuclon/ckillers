package fr.ck.daos;

import fr.ck.entite.Inscrit;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InscritDao {
    /**
     * Liste des inscrits pour la liste des administrateurs
     * @return
     */
    public List<Inscrit> listInscrit() {
        String query = "SELECT * FROM inscrit ORDER BY nom;";
        List<Inscrit> listeDInscrit = new ArrayList<>();

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
        {
            while (resultSet.next()) {
                listeDInscrit.add(new Inscrit(resultSet.getInt("idInscrit"),resultSet.getString("nom")
                        ,resultSet.getString("prenom"),resultSet.getString("mail"),resultSet.getString("telephone")
                        ,resultSet.getString("adresse"),resultSet.getString("statut"),resultSet.getString("dateAdhesion")
                        ,resultSet.getString("dateInscription"),resultSet.getInt("nbrPartieJouees"),"X"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listeDInscrit;
    }

    public void modifierStatutInscrit(Integer id, String statut) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("update Inscrit set statut=? where idInscrit=?;")) {
            statement.setString(1, statut);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'obtenir les informations sur un inscrit à partir de son mail
     * L'adresse mail est unique dans la base de données
     * @param mail
     * @return
     */
    public Inscrit getInscritParMail(String mail){
        String query = "SELECT * FROM inscrit WHERE mail=?";

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,mail);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Inscrit(resultSet.getInt("idInscrit"),resultSet.getString("nom")
                            ,resultSet.getString("prenom"),resultSet.getString("mail"),resultSet.getString("telephone")
                            ,resultSet.getString("adresse"),resultSet.getString("statut"),resultSet.getString("dateAdhesion")
                            ,resultSet.getString("dateInscription"),resultSet.getInt("nbrPartieJouees"),resultSet.getString("motDePasse"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet d'obtenir l'id d'un inscrit à partir de son mail
     * L'adresse mail est unique dans la base de données
     * @param mail
     * @return
     */
    public Integer getIdParMail(String mail){
        String query = "SELECT idInscrit FROM inscrit WHERE mail=?";

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1,mail);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("idInscrit");
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet d'obtenir les informations de l'inscrit ayant validé la partie dont l'entrée est en paramètre
     * @param idPartie
     * @return
     */
    public Inscrit getInscritValideur(Integer idPartie){
        String query = "SELECT inscrit.idInscrit,nom,prenom FROM inscrit INNER JOIN partie WHERE idInscrit_1=inscrit.idInscrit AND idPartie=?";

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1,idPartie);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Inscrit(resultSet.getInt("idInscrit"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtenir les informations nécessaires pour la connexion d'un inscrit
     * @param mail
     * @return Inscrit(mail,motdepasse,statut)
     */
    public Inscrit getConnexion(String mail){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT motDePasse,statut FROM inscrit WHERE mail=?")
        ) {
            statement.setString(1,mail);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Inscrit(mail,
                            resultSet.getString("motDePasse"),
                            resultSet.getString("statut"));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Inscrit("","");
    }

    /**
     * Permet d'ajouter un nouvel inscrit à la base de données
     * @param inscrit
     */
    public void ajouterInscrit(Inscrit inscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO inscrit(nom,prenom,mail,adresse,statut,dateInscription,nbrPartieJouees,motDePasse) VALUES (?,?,?,?,?,?,?,?)")){
            statement.setString(1,inscrit.getNom());
            statement.setString(2,inscrit.getPrenom());
            statement.setString(3,inscrit.getMail());
            statement.setString(4,inscrit.getAdresse());
            statement.setString(5,"inscrit");
            statement.setString(6,inscrit.getDateInscription());
            statement.setInt(7,0);
            statement.setString(8,inscrit.getMotDePasse());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de l'insertion de l'inscrit à la base", e);
        }
    }
}
