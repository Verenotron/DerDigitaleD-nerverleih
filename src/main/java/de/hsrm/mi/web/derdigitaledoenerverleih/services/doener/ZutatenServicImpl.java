package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.ZutatRepository;

@Service
public class ZutatenServicImpl implements ZutatenService{

    @Autowired ZutatRepository zutatRepository;

    @Override
    public Collection<Zutat> findAllZutaten() {
        return zutatRepository.findAll(Sort.by("name"));
    }

    @Override
    public Optional<Zutat> findZutatById(String ean) {
        return zutatRepository.findById(ean);
    }

    
}
