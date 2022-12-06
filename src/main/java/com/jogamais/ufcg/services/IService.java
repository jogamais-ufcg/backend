package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.User;

import java.util.List;

public interface IService<T> {

	public Object getById(Long id) throws UserException;

	public abstract void save(User user) throws UserException;

	//public abstract void delete(User user) throws UserException;

	public abstract void deleteById(Long id) throws UserException;

	public abstract List<T> findAll() throws UserException;
}
