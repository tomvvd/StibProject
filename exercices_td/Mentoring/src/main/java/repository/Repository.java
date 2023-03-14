package repository;

import dto.Dto;
import exception.RepositoryException;

import java.util.List;

public interface Repository<K,T extends Dto<K>> {
    public void add(T item) throws RepositoryException;
    public void remove(K key) throws RepositoryException;
    public T get(K key) throws RepositoryException;
    public List<T> getAll() throws RepositoryException;
    public boolean contains(K key) throws RepositoryException;
}
