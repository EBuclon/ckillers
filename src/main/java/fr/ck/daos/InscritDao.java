package fr.ck.daos;

import fr.ck.entite.Inscrit;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InscritDao {

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
                        ,resultSet.getString("dateInscription"),resultSet.getInt("nbrPartieJouees"),"XXX"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Inscrit getInscritByMail(String mail){
        String query = "SELECT * FROM inscrit WHERE mail=?";

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
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

    public void ajouterInscrit(Inscrit inscrit){
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO inscrit(nom,prenom,mail,adresse,dateInscription,motDePasse) VALUES (?,?,?,?,?,?)")){ //,Statement.RETURN_GENERATED_KEYS
            statement.setString(1,inscrit.getNom());
            statement.setString(2,inscrit.getPrenom());
            statement.setString(3,inscrit.getMail());
            statement.setString(4,inscrit.getAdresse());
            statement.setString(5,"2018-02-01");//inscrit.getDateInscription()
            statement.setString(6,inscrit.getMotDePasse());
            statement.executeUpdate();

            /*try (ResultSet ids = statement.getGeneratedKeys()){
                int generatedId = ids.getInt(1);
                inscrit.setIdInscrit(generatedId);
                return inscrit;
            }*/
        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de l'insertion de l'inscrit à la base", e);
        }


    }

}
