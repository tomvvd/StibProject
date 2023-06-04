package g58594.atlg4.stibRide.model.dao;

import g58594.atlg4.stibRide.model.ConfigManager;
import g58594.atlg4.stibRide.model.dto.Pair;
import g58594.atlg4.stibRide.model.repository.RepositoryException;
import g58594.atlg4.stibRide.model.dto.StopDto;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StopDao {

    private Statement statement;

    private StopDao(){
        String url = ConfigManager.getInstance().getProperties("db.url");
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            statement = connexion.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static StopDao getInstance(){
        return StopDaoHolder.INSTANCE;
    }

    private static class StopDaoHolder{
        private static final StopDao INSTANCE = new StopDao();
    }

    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
        if(key == null){
            throw new RepositoryException("No key has been given");
        }
        StopDto stopDto = null;
        String query = "SELECT id_line,id_station,id_order,name FROM STOPS JOIN STATIONS on id_station = id WHERE id_line = "+key.getFirst()+" AND id_station = "+key.getSecond();
        try{
            ResultSet res = statement.executeQuery(query);

            int count = 0;
            while (res.next()){
                stopDto = new StopDto(res.getInt(1),res.getInt(2),res.getInt(3), res.getString(4));
                count++;
            }
            if (count>1){
                throw new RepositoryException("Not unique record = "+ key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return stopDto;
    }

    public List<StopDto> getAll() throws RepositoryException {
        List<StopDto> list = new ArrayList<>();
        String query = "SELECT id_line, id_station, id_order, name FROM STOPS "
                + "JOIN STATIONS on id_station = id ORDER by id_line, id_order";

        try{
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                list.add(new StopDto(res.getInt(1),res.getInt(2),res.getInt(3),res.getString(4)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return list;
    }
}
