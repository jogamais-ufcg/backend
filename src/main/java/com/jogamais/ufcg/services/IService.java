package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.exceptions.UserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IService<T> {

    public abstract T create(T entity) throws UserException;

	public abstract void deleteById(Long id) throws UserException, CourtException;

    public abstract Page<T> findAll(PageRequest pageRequest);

    public abstract Page<T> search(String searchTerm, int page, int size);

}
