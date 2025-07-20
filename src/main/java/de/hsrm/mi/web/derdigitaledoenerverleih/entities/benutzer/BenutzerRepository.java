package de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; // ! -> stellt EntityManager zur verfügung
// ! dieser verwendet den Persistance Context(Cache) für effizientere Datenverwaltung in der DB

public interface BenutzerRepository extends JpaRepository<Benutzer, String>{
    //Benutzerrepository nutzt intern den EntityManager. EntityManager wird vom Spring Data JPA automatisch verwaltet und ist im Repository versteckt

    Optional<Benutzer> findByLoginName(String name); //Wird automatisch von JPA implementiert.
    
}
