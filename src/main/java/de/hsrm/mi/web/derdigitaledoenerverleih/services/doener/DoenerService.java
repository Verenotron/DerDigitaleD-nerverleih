package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.Collection;
import java.util.Optional;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.ui.doener.DoenerException;

public interface DoenerService {
    Doener saveDoener(Doener doener) throws DoenerException;
    Optional<Doener> findDoenerById(long id);
    Collection<Doener> findAllDoener();
    void deleteDoenerById(long id);
    
}
