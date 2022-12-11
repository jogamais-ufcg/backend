package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourtService implements IService<Court> {
    @Autowired
    private CourtRepository courtRepository;

    public Court getById(Long id) {

        return null;
    }

    public void save(Court court) {
        courtRepository.save(court);
    }

    public void deleteById(Long id) {

    }

    public List<Court> findAll() {

        return null;
    }
}
