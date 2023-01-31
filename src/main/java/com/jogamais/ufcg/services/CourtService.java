package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.CourtException;
import com.jogamais.ufcg.exceptions.CourtInvalidAppointmentDuration;
import com.jogamais.ufcg.exceptions.CourtInvalidOpeningHours;
import com.jogamais.ufcg.exceptions.CourtInvalidRecurrenceIntervalPeriod;
import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.CourtRules;
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
    public Court create(Court court) { return courtRepository.save(court); }

    public Court createCourtValidatingFields(Court court) throws CourtException, CourtInvalidOpeningHours, CourtInvalidAppointmentDuration, CourtInvalidRecurrenceIntervalPeriod {
        Court foundCourt = courtRepository.findByName(court.getName());
        if (foundCourt != null) {
            throw new CourtException();
        }
        validateCourtRulesFields(court.getCourtRules());
        return create(court);
    }

    private void validateCourtRulesFields(CourtRules courtRules) throws CourtInvalidOpeningHours, CourtInvalidAppointmentDuration, CourtInvalidRecurrenceIntervalPeriod {
        if (courtRules.getOpeningHour() >= courtRules.getClosingHour()) {
            throw new CourtInvalidOpeningHours();
        }

        if (courtRules.getAppointmentDuration() < 60 && (courtRules.getAppointmentDuration() % 30) != 0) {
            throw new CourtInvalidAppointmentDuration();
        }

        if (courtRules.getRecurrenceIntervalPeriod() < 7) {
            throw new CourtInvalidRecurrenceIntervalPeriod();
        }

    }

    public void deleteById(Long id) throws CourtException {
        Court court = getById(id);
        courtRepository.delete(court);
    }

    public List<Court> findAll() {
        List<Court> courts = courtRepository.findAll();
        return courts;
    }

    public Court getByName(String name) throws CourtException, CourtInvalidOpeningHours, CourtInvalidAppointmentDuration, CourtInvalidRecurrenceIntervalPeriod {
        Court foundCourt = courtRepository.findByName(name);
        if (foundCourt != null) {
            throw new CourtException();
        }
        validateCourtRulesFields(foundCourt.getCourtRules());

        return foundCourt;
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
