package g58594.atlg4.stibRide.model.dao;

import g58594.atlg4.stibRide.model.ConfigManager;
import g58594.atlg4.stibRide.model.dto.FavoriDto;
import g58594.atlg4.stibRide.model.repository.RepositoryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FavoriDaoTest {
    private final FavoriDto bob;

    private final FavoriDto elio;

    private final List<FavoriDto> all;

    private FavoriDao instance;

    public FavoriDaoTest() {
        bob = new FavoriDto("bob", "ERASME", "BIZET");
        elio = new FavoriDto("elio", "MERODE", "AUMALE");

        all = new ArrayList<>();
        all.add(bob);
        all.add(elio);

        try {
            ConfigManager.getInstance().load();
            instance = FavoriDao.getInstance();
        } catch (IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", ex);
        }
    }

    @Test
    public void testDelete_Add() throws Exception {
        instance.delete(bob.getKey());
        instance.add(bob.getKey(), bob.getOrigin(), bob.getDestination());
    }

    @Test
    public void testGetExist() throws Exception {
        //Arrange
        FavoriDto expected = bob;
        //Action
        FavoriDto result = instance.get(bob.getKey());
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetNotExist() throws Exception {
        assertNull(instance.get("error"));
    }

    @Test
    public void testGetALL() throws Exception {
        //Action
        List<FavoriDto> result = instance.getAll();

        Set<FavoriDto> ensembleAttendu = new HashSet<>(all);
        Set<FavoriDto> ensembleObtenu = new HashSet<>(result);
        //Assert
        assertEquals(ensembleAttendu, ensembleObtenu);
    }

    @Test
    public void testModify() throws Exception {
        FavoriDto favoriDto = new FavoriDto("test", "AUMALE", "ALMA");
        instance.add(favoriDto.getKey(),favoriDto.getOrigin(),favoriDto.getDestination());
        instance.modify(favoriDto.getKey(),"ERASME", favoriDto.getDestination());
        FavoriDto result = instance.get(favoriDto.getKey());
        assertNotEquals(result.getOrigin(),favoriDto.getOrigin());
        instance.delete(favoriDto.getKey());
    }
}