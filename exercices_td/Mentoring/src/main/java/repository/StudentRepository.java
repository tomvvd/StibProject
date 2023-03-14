package repository;

import dto.StudentDto;
import exception.RepositoryException;

import java.util.List;

public class StudentRepository implements Repository<Integer,StudentDto> {
    private final StudentDataAccessObject stdDAO;

    public StudentRepository() {
        this.stdDAO = StudentDataAccessObject.getInstance();
    }

    public StudentRepository(StudentDataAccessObject dao) {
        this.stdDAO = dao;
    }

    @Override
    public void add(StudentDto item) throws RepositoryException {
        if(contains(item.getKey())){
            stdDAO.update(item);
        }else {
            stdDAO.insert(item);
        }
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        stdDAO.delete(key);
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        return stdDAO.get(key);
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        return stdDAO.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return stdDAO.get(key) != null;
    }
}
