package de.hsrm.mi.web.derdigitaledoenerverleih.services.entleihung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.messaging.FrontendNachrichtEvent;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer.BenutzerServiceImpl;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.DoenerServiceImpl;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;

@Service
public class EntleihungServiceImpl implements EntleihungService{

    @Autowired BenutzerServiceImpl benutzerService;
    @Autowired DoenerServiceImpl doenerService;

    private final ApplicationEventPublisher eventPublisher;

    public EntleihungServiceImpl(@Autowired ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void entleiheDoener(long doenerId, String loginName) {

        try{
            Benutzer benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new RuntimeException("Bentuzer konnte nicht gefunden werden"));
            Doener doener = doenerService.findDoenerById(doenerId).orElseThrow(() -> new RuntimeException("Doener konnte nicht gefunden werden"));
            doener.setEntliehen(1);
            benutzer.entleiheDoener(doener);
            benutzerService.saveBenutzer(benutzer);
            doenerService.saveDoener(doener);
            eventPublisher.publishEvent(new FrontendNachrichtEvent(FrontendNachrichtEvent.Typ.DOENER, doener.getId(), FrontendNachrichtEvent.Action.BOOKED));
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    @Override
    public void zurueckgebeDoener(long doenerId, String loginName) {
        
        try{
            Benutzer benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new RuntimeException("Bentuzer konnte nicht gefunden werden"));
            Doener doener = doenerService.findDoenerById(doenerId).orElseThrow(() -> new RuntimeException("Doener konnte nicht gefunden werden"));
            doener.gebeZurueck(1);
            benutzer.gebeDoenerZurueck(doener, 1);
            benutzerService.saveBenutzer(benutzer);
            doenerService.saveDoener(doener);
            eventPublisher.publishEvent(new FrontendNachrichtEvent(FrontendNachrichtEvent.Typ.DOENER, doener.getId(), FrontendNachrichtEvent.Action.RETURNED));
        }catch(Exception e){
            System.out.println("Das zurückgeben des Doeners hat nicht geklappt: " + e.getMessage());
        }

    }

    @Override
    public List<Doener> findeEntleihungenVonBenutzer(String loginName) {
        try{
            Benutzer benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new RuntimeException("Bentuzer konnte nicht gefunden werden"));
            return benutzer.getEntlieheneDoener();
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
    
}
