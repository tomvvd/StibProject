package g58594.atlg4.stibRide.model.repository;

import g58594.atlg4.stibRide.model.dao.StopDao;
import g58594.atlg4.stibRide.model.dto.Pair;
import g58594.atlg4.stibRide.model.dto.StopDto;

import java.util.List;

public class StopRepository {
    private final StopDao stopDao;

    public StopRepository() {
        this.stopDao = StopDao.getInstance();
    }

    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
         return stopDao.get(key);
    }

    public List<StopDto> getAll() throws RepositoryException {
        return stopDao.getAll();
    }

    public boolean contains(Pair<Integer, Integer> key) throws RepositoryException {
        return stopDao.get(key) != null;
    }
}
