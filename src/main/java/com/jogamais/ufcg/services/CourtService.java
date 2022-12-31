package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService implements IService<Court> {
    @Autowired
    private CourtRepository courtRepository;

    public Court getById(Long id) throws CourtException {
        return courtRepository.findById(id).orElseThrow(CourtException::new);
    }

    @Override
    public Court create(Court court) {
        return courtRepository.save(court);
    }

    public void deleteById(Long id) throws CourtException {
        Court court = getById(id);
        courtRepository.delete(court);
    }

    public List<Court> findAll() {
        List<Court> courts = courtRepository.findAll();
        return courts;
    }

    @Override
    public Page<Court> findAll(PageRequest page) {
        return courtRepository.findAll(PageRequest.of(page.getPageNumber(), 5));
    }

    @Override
    public Page<Court> search(String searchTerm, int page, int size) {
        return null;
    }
}
