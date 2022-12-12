package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.UserException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<T> {

    public abstract Object getById(Long id) throws UserException;

    public abstract T create(T entity) throws UserException;

    public abstract void deleteById(Long id) throws UserException;

    public abstract List<T> findAll();

    public abstract Page<T> search(String searchTerm, int page, int size);
}
