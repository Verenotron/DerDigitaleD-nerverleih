package de.hsrm.mi.web.derdigitaledoenerverleih.services.entleihung;

import java.util.List;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;

public interface EntleihungService {

    void entleiheDoener(long doenerId, String loginName);
    void zurueckgebeDoener(long doenerId, String loginName);
    List<Doener> findeEntleihungenVonBenutzer(String loginName);
    
}
