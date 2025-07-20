package de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, String>{

    Optional<Benutzer> findByLoginName(String name); //Wird automatisch von JPA implementiert.
    
}
