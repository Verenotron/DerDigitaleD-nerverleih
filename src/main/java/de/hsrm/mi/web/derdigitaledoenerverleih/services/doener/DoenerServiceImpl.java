package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.DoenerRepository;
import de.hsrm.mi.web.derdigitaledoenerverleih.ui.doener.DoenerException;

@Service
public class DoenerServiceImpl implements DoenerService{

    @Autowired DoenerRepository doenerRepository;

    @Override
    public Doener saveDoener(Doener doener) throws DoenerException {
        Doener d;
        try{
            d = doenerRepository.save(doener);
        }catch(Exception e){
            throw new DoenerException("Fehler beim Speichern des Döners.");
        }   
        return d;
    }

    @Override
    public Optional<Doener> findDoenerById(long id) {
        return doenerRepository.findById(id);
    }

    @Override
    public Collection<Doener> findAllDoener() {
        return doenerRepository.findAll();
    }

    @Override
    public void deleteDoenerById(long id) {
        doenerRepository.deleteById(id);
    }
    
}
