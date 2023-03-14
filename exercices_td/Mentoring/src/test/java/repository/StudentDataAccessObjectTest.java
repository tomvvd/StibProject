package repository;

import dto.StudentDto;
import exception.RepositoryException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataAccessObjectTest {
    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 12_345;
    private static final String FILE_URL = "data/test_repo_students.txt";

    private final String url;

    private final List<StudentDto> all;

    public StudentDataAccessObjectTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(new StudentDto(64_931, "Olsen", "Maggy"));
        all.add(new StudentDto(73_780, "Frost", "Phoebe"));
        all.add(new StudentDto(94_853, "Ortega", "Skyler"));
        all.add(new StudentDto(93_371, "Blankenship", "Byron"));
        all.add(new StudentDto(82_227, "Cote", "Molly"));
        all.add(bob);

        url = getClass().getClassLoader()
                .getResource(FILE_URL)
                .getFile().substring(3);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testSelectExist");
        //Arrange
        StudentDto expected = bob;
        System.out.println(url);
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        //Action
        StudentDto result = dao.get(KEY);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testSelectNotExist");
        //Arrange
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        //Action
        StudentDto result = dao.get(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            dao.get(incorrect);
        });
    }

    @Test
    public void testGetWhenFileNotFound() throws Exception {
        System.out.println("testSelectWhenFileNotFound");
        //Arrange
        String url = "test/NoFile.txt";
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            StudentDataAccessObject dao = new StudentDataAccessObject(url);
            dao.get(KEY);
        });
    }

    @Test
    public void testUpdateExist() throws Exception {
        System.out.println("testUpdateExist");
        //Arrange
        StudentDto expected = new StudentDto(KEY, "Sandy","L'écureil");
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        //Action
        dao.update(expected);
        StudentDto result = dao.get(KEY);
        System.out.println(result);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateNotExist() throws Exception {
        System.out.println("testUpdateNotExist");
        //Arrange
        StudentDto expected = new StudentDto(58594, "Sandy","L'écureil");
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        //Action
        dao.update(expected);
        StudentDto result = dao.get(58594);
        System.out.println(result);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        StudentDataAccessObject dao = new StudentDataAccessObject(url);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            dao.get(incorrect);
        });
    }

    @Test
    public void testUpdateWhenFileNotFound() throws Exception {
        System.out.println("testSelectWhenFileNotFound");
        //Arrange
        String url = "test/NoFile.txt";
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            StudentDataAccessObject dao = new StudentDataAccessObject(url);
            dao.get(KEY);
        });
    }
}