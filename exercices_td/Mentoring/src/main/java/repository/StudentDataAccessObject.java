package repository;

import config.ConfigManager;
import dto.StudentDto;
import exception.RepositoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StudentDataAccessObject implements DataAccessObject<Integer,StudentDto> {
    /**
     * Creates a new instance of StudentDao with a specific file.
     * @param url
    **/
    StudentDataAccessObject(String url){
        this.path = Paths.get(url);
    }


    private final Path path;

    private StudentDataAccessObject() {
        try{
            ConfigManager.getInstance().load();
        }catch (IOException e){
            System.out.println("Chargement de configuration impossible"+ e.getMessage());
        }
        String fileUrl = ConfigManager.getInstance().getProperties("file.url");
        this.path = Paths.get(fileUrl);
    }

    public static StudentDataAccessObject getInstance() {
        return StudentDaoHolder.INSTANCE;
    }

    private static class StudentDaoHolder {
        private static final StudentDataAccessObject INSTANCE = new StudentDataAccessObject();
    }


    @Override
    public void insert(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élement en paramètre " + item);
        }
        try {
            String lineToInsert = item.toString()+ System.lineSeparator();
            Files.write(path, lineToInsert.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucun élement en paramètre " + key);
        }
        try{
            List<String> lines = Files.readAllLines(path);
            boolean stop = false;
            int index = -1;
            for (int i = 0; i < lines.size() && !stop; i++) {
                String[] attributs = lines.get(i).split(",");
                if(key == Integer.parseInt(attributs[0])){
                    index = i;
                    stop = true;
                }
            }
            if(index>-1) {
                lines.remove(index);
                Files.write(path, lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élement en paramètre " + item);
        }
        delete(item.getKey());
        insert(item);
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé en paramètre " + key);
        }
        StudentDto result = null;

        try{
            List<String> lines = Files.readAllLines(path);
            boolean stop = false;
            for (int i = 0; i < lines.size() && !stop; i++) {
                String[] attributs = lines.get(i).split(",");
                if(key == Integer.parseInt(attributs[0])){
                    result = buildDto(lines.get(i));
                    stop = true;
                }
            }
        }catch(IOException e){
            throw new RepositoryException(e);
        }
        return result;
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        List<StudentDto> result = new ArrayList<>();

        try{
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                StudentDto studentDto = buildDto(line);
                result.add(studentDto);
            }
        }catch(IOException e){
            throw new RepositoryException(e);
        }

        return result;
    }

    private StudentDto buildDto(String line) {
        String[] splited = line.split(",");
        int matricule = Integer.parseInt(splited[0]);
        String lastName = splited[1];
        String fisrtName = splited[2];
        return new StudentDto(matricule, lastName, fisrtName);
    }
}
