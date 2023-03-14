package repository;

import dto.Dto;
import exception.RepositoryException;

import java.util.List;

public interface DataAccessObject<K,T extends Dto<K>> {
    public void insert(T item) throws RepositoryException;
    public void delete(K key) throws RepositoryException;
    public void update(T item) throws RepositoryException;
    public T get(K key) throws RepositoryException;
    public List<T> getAll() throws RepositoryException;
}
