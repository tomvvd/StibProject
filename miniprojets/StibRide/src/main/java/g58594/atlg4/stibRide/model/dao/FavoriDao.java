package g58594.atlg4.stibRide.model.dao;

import g58594.atlg4.stibRide.model.ConfigManager;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.dto.StopDto;
import g58594.atlg4.stibRide.model.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriDao {
    private Statement statement;

    private FavoriDao(){
        String url = ConfigManager.getInstance().getProperties("db.url");
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            statement = connexion.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static FavoriDao getInstance(){
        return FavoriDaoHolder.INSTANCE;
    }

    private static class FavoriDaoHolder{
        private static final FavoriDao INSTANCE = new FavoriDao();
    }

    public FavoriDto get(String key) throws RepositoryException {
        if(key == null){
            throw new RepositoryException("No key has been given");
        }
        FavoriDto favoriDto = null;
        String query = "SELECT name,origin,destination FROM FAVORIS WHERE name ='"+ key+"'";
        try{
            ResultSet res = statement.executeQuery(query);

            int count = 0;
            while (res.next()){
                favoriDto = new FavoriDto(res.getString(1),res.getString(2),res.getString(3));
                count++;
            }
            if (count>1){
                throw new RepositoryException("Not unique record = "+ key);
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
            ResultSet res = statement.executeQuery(query);
            while (res.next()){
                list.add(new FavoriDto(res.getString(1),res.getString(2),res.getString(3)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return list;
    }

    public void modify(String key,String origin, String dest) throws RepositoryException {
        String query = "UPDATE FAVORIS SET origin='"+origin+"',destination='"+dest+"' where name='"+key+"'";
        try{
            int count = statement.executeUpdate(query);
            System.out.println("Nombre de lignes modifiées : "+count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void delete(String key) throws RepositoryException {
        String query = "DELETE FROM FAVORIS WHERE name ='"+key+"'";
        try{
            int count = statement.executeUpdate(query);
            System.out.println("Nombre de lignes supprimées : "+count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void add(String key, String origin, String dest) throws RepositoryException {
        String query = "INSERT INTO FAVORIS(name,origin,destination) values('"+key+"','"+origin+"','"+dest+"')";
        try{
            int count = statement.executeUpdate(query);
            System.out.println("Nombre de lignes ajoutées : " + count);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
