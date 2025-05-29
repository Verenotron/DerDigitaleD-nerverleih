package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.Collection;
import java.util.Optional;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;

public interface ZutatenService {

    Collection<Zutat> findAllZutaten();
    Optional<Zutat> findZutatById(String ean);
    
}
