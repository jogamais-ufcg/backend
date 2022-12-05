package com.jogamais.ufcg.services;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourtService implements IService<Court> {

    @Autowired
    private CourtRepository courtRepository;

    public Court getById(Long id) {

        return null;
    }

    public Court save(Court entity) {
        return entity;
    }

    public void delete(Court entity) {

    }

    public void deleteById(Long id) {

    }

    public List<Court> findAll() {

    }
}
