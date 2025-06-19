package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;

@Service
public class DoenerErfindungsService {

    @Autowired DoenerNamingService doenerNamingService;
    @Autowired ZutatenService zutatenService;

    private List<Zutat> generierteZutaten = new ArrayList<Zutat>();

    public Doener generiereDoener(){
        Doener doener = new Doener();
        //doener.setId(0);
        doener.setBezeichnung(this.getName());
        doener.setBestand(this.getBestand());
        doener.setPreis(this.getPreis());
        doener.setVegetarizitaet(this.getVegetarizitaet());
        doener.setZutaten(this.generiereZutaten());
        return doener;
    }

    public List<Zutat> generiereZutaten(){
        List<Zutat> alleZutaten = new ArrayList<>(zutatenService.findAllZutaten());
        Collections.shuffle(alleZutaten);
        int max = Math.max(5, alleZutaten.size() / 4);
        return alleZutaten.subList(0, max);
    }

    public int getVegetarizitaet(){
        int wert = 0;

        for(Zutat zutat : generierteZutaten){
            if(zutat.getVegetarizitaet() > wert){
                wert = zutat.getVegetarizitaet();
            }
        }
        return wert;
    }

    public int getBestand(){
        return new Random().nextInt(100) + 1;
    }

    public int getPreis(){
        return new Random().nextInt(29) + 5;
    }

    public String getName(){
        return doenerNamingService.getName() + "dön";
    }
    
}
