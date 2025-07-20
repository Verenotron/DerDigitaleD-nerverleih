package de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.BenutzerRepository;
import de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer.BenutzerException;

@Service
public class BenutzerServiceImpl implements BenutzerService{

    @Autowired BenutzerRepository benutzerRepository;

    @Override
    public Benutzer saveBenutzer(Benutzer benutzer) throws BenutzerException {
        Benutzer b;
        try{
            b = benutzerRepository.save(benutzer); 
            // ! save methode ist eine vom EntityManager. Stellt Zugriff auf PersistanceContext bereit
            // ! PersistanceContext ist der Zwischenspeicher (Cache) von Entities, damit man bei gleicher Entität nicht auf DB zugreift(z.B. bei save() aufruf mit gleicher Entität) 
            // ! Dadurch werden DB-Zugriffe optimiert und effizienter gearbeitet. 
            // ! -> also Objekte im arbeitsspeicher
        }catch(Exception e){
            throw new BenutzerException("Fehler beim Speichern des Benutzers...");
        }
        
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
