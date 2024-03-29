package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.AppointmentException;
import com.jogamais.ufcg.exceptions.UserAlreadyHasAppointmentException;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.exceptions.UserMissingEnrollmentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IService<T> {

    public abstract T create(T entity) throws UserException, UserMissingEnrollmentException, AppointmentException, UserAlreadyHasAppointmentException;

    public abstract Page<T> findAll(PageRequest pageRequest);

    public abstract Page<T> search(String searchTerm, int page, int size);

}
