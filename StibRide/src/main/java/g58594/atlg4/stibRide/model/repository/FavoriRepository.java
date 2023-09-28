package g58594.atlg4.stibRide.model.repository;

import g58594.atlg4.stibRide.model.dao.FavoriDao;
import g58594.atlg4.stibRide.model.dao.StopDao;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.dto.Pair;
import g58594.atlg4.stibRide.model.dto.StopDto;

import java.sql.SQLException;
import java.util.List;

public class FavoriRepository {
    private final FavoriDao favoriDao;

    public FavoriRepository() {
        this.favoriDao = FavoriDao.getInstance();
    }

    public FavoriRepository(FavoriDao favoriDao) {
        this.favoriDao = favoriDao;
    }

    public FavoriDto get(String key) throws RepositoryException {
        return favoriDao.get(key);
    }

    public List<FavoriDto> getAll() throws RepositoryException {
        return favoriDao.getAll();
    }

    public boolean contains(String key) throws RepositoryException {
        return get(key) != null;
    }

    public void modify(String key,String origin, String dest) throws RepositoryException {
        favoriDao.modify(key,origin,dest);
    }

    public void delete(String key) throws RepositoryException {
        favoriDao.delete(key);
    }

    public void add(String key, String origin, String dest) throws RepositoryException {
        favoriDao.add(key,origin,dest);
    }
}
