package de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer;

import java.util.Collection;
import java.util.Optional;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer.BenutzerException;

public interface BenutzerService {
    Benutzer saveBenutzer(Benutzer benutzer) throws BenutzerException ;
    Optional<Benutzer> findBenutzerById(String loginName);
    Collection<Benutzer> findAllBenutzer();
    void deleteBenutzerById(String loginName);
}
