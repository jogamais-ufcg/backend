package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.User;

import java.util.List;

public interface IService<T> {

	public abstract Object getById(Long id) throws UserException, CourtException;

	public abstract T create(T entity) throws UserException;

	public abstract void deleteById(Long id) throws UserException, CourtException;

	public abstract List<T> findAll();
}
