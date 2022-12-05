package com.jogamais.ufcg.services;

import java.util.List;

public interface IService<T> {

	public abstract T getById(Long id);

	public abstract T save(T entity);

	public abstract void delete(T entity);

	public abstract void deleteById(Long id);

	public abstract List<T> findAll();
}
