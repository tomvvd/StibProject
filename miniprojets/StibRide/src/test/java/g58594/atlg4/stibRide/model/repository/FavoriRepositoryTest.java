package g58594.atlg4.stibRide.model.repository;

import g58594.atlg4.stibRide.model.dao.FavoriDao;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class FavoriRepositoryTest {

    @Mock
    private FavoriDao mock;

    private final FavoriDto ecole;

    private final FavoriDto test;

    private static final String KEY = "ecole";

    private final List<FavoriDto> all;

    public FavoriRepositoryTest() {
        ecole = new FavoriDto(KEY, "ERASME", "BIZET");
        test = new FavoriDto("test", "PARC", "MERODE");

        all = new ArrayList<>();
        all.add(ecole);
    }

    @BeforeEach
    void init() throws RepositoryException {
        Mockito.lenient().when(mock.get(KEY)).thenReturn(ecole);
        Mockito.lenient().when(mock.get(test.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
    }

    @Test
    public void testGetExist() throws Exception {
        //Arrange
        FavoriDto expected = ecole;
        FavoriRepository repository = new FavoriRepository(mock);
        //Action
        FavoriDto result = repository.get(KEY);
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).get(KEY);
    }

    @Test
    public void testGetAll() throws Exception {
        //Arrange
        FavoriRepository repository = new FavoriRepository(mock);
        List<FavoriDto> result = repository.getAll();
        //Action
        List<FavoriDto> expected = all;

        Set<FavoriDto> ensembleAttendu = new HashSet<>(all);
        Set<FavoriDto> ensembleObtenu = new HashSet<>(result);
        //Assert
        assertEquals(ensembleAttendu, ensembleObtenu);
        Mockito.verify(mock, times(1)).getAll();
    }


}