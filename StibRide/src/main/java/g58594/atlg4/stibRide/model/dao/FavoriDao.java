package g58594.atlg4.stibRide.model.dao;

import g58594.atlg4.stibRide.model.ConfigManager;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.dto.StopDto;
import g58594.atlg4.stibRide.model.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriDao {
    private Connection connexion;

    private FavoriDao(){
        try {
            connexion = DBManager.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static FavoriDao getInstance(){
        return FavoriDaoHolder.INSTANCE;
    }

    private static class FavoriDaoHolder{
        private static final FavoriDao INSTANCE = new FavoriDao();
    }

    public FavoriDto get(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        FavoriDto favoriDto = null;
        String query = "SELECT name, origin, destination FROM FAVORIS WHERE name = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, key);
            ResultSet res = statement.executeQuery();

            int count = 0;
            while (res.next()) {
                favoriDto = new FavoriDto(res.getString(1), res.getString(2), res.getString(3));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Not unique record = " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return favoriDto;
    }

    public List<FavoriDto> getAll() throws RepositoryException {
        List<FavoriDto> list = new ArrayList<>();
        String query = "SELECT name,origin,destination FROM FAVORIS";
        try{
            Statement statement = connexion.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()){
                list.add(new FavoriDto(res.getString(1),res.getString(2),res.getString(3)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return list;
    }

    public void modify(String key, String origin, String dest) throws RepositoryException {
        String query = "UPDATE FAVORIS SET origin=?, destination=? WHERE name=?";
        try {
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, origin);
            statement.setString(2, dest);
            statement.setString(3, key);

            int count = statement.executeUpdate();
            System.out.println("Nombre de lignes modifiées : " + count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void delete(String key) throws RepositoryException {
        String query = "DELETE FROM FAVORIS WHERE name = ?";
        try {
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, key);

            int count = statement.executeUpdate();
            System.out.println("Nombre de lignes supprimées : " + count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void add(String key, String origin, String dest) throws RepositoryException {
        String query = "INSERT INTO FAVORIS(name,origin,destination) values(?,?,?)";
        try {
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, key);
            statement.setString(2, origin);
            statement.setString(3, dest);

            int count = statement.executeUpdate();
            System.out.println("Nombre de lignes ajoutées : " + count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
