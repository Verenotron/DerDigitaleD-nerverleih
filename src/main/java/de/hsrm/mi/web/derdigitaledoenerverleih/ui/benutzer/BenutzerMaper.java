package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;

public class BenutzerMaper {

    BenutzerFormular benutzerToBenutzerFormular(Benutzer benutzer){
        BenutzerFormular benutzerFormular = new BenutzerFormular();
        benutzerFormular.setName(benutzer.getName());
        benutzerFormular.setEmail(benutzer.getEmail());
        benutzerFormular.setVegetarizitaet(benutzer.getVegetarizitaet());
        benutzerFormular.setRolle(benutzer.getRolle());
        benutzerFormular.setLosung(benutzer.getLosung());
        return benutzerFormular;
    }

    Benutzer benutzerFormularToBenutzer(BenutzerFormular formular){
        Benutzer benutzer = new Benutzer();
        benutzer.setName(formular.getName());
        benutzer.setEmail(formular.getEmail());
        benutzer.setVegetarizitaet(formular.getVegetarizitaet());
        benutzer.setRolle(formular.getRolle());
        benutzer.setLosung(formular.getLosung());
        return benutzer;
    }
}
