package de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerServiceImpl implements BenutzerService{

    @Autowired BenutzerRepository benutzerRepository;

    @Override
    public Benutzer saveBenutzer(Benutzer benutzer) {
        Benutzer b = benutzerRepository.save(benutzer);
        return b;
    }

    @Override
    public Optional<Benutzer> findBenutzerById(String loginName) {
        return benutzerRepository.findById(loginName);
    }

    @Override
    public Collection<Benutzer> findAllBenutzer() {
        return benutzerRepository.findAll();
    }

    @Override
    public void deleteBenutzerById(String loginName) {
        benutzerRepository.deleteById(loginName);
    }
    
}
