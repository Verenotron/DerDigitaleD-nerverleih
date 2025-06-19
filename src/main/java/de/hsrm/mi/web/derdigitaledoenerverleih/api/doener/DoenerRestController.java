package de.hsrm.mi.web.derdigitaledoenerverleih.api.doener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.DoenerErfindungsService;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.DoenerServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DoenerRestController {

    @Autowired DoenerServiceImpl doenerservice;
    @Autowired DoenerErfindungsService doenerErfindungsService;

    @GetMapping("/api/doener/{id}")
    public ResponseEntity<DoenerDTO> getDoener(@PathVariable("id") int id){
        return doenerservice.findDoenerById(id).map(doener -> ResponseEntity.ok(DoenerDTO.fromEntity(doener))).orElseGet(() -> ResponseEntity.status(404).build()) ;
    }

    @PostMapping("/api/generiereDoener")
    public DoenerDTO generiereDoener(){
        Doener doener = doenerErfindungsService.generiereDoener();
        try{
            doener = doenerservice.saveDoener(doener);
        }catch(Exception e){
            System.out.println("Etwas ist beim generieren des doeners schief gelaufen.");
        }
        
        return DoenerDTO.fromEntity(doener);
    }

    @GetMapping("/api/doener")
    public Collection<DoenerDTO> getDoenerListe(){
        Collection<Doener> alleDoener = null;
        List<DoenerDTO> alleDTO = new ArrayList<DoenerDTO>();
        try{
            alleDoener = doenerservice.findAllDoener();

        }catch(Exception e){

        }

        for(Doener d : alleDoener){
            alleDTO.add(DoenerDTO.fromEntity(d));
        }

        return alleDTO; // ! Man kann auch die Doener entität so übergeben, funktioniert auch
    }

    //! MapStruct (Siehe augabenblatt 6) anschauen (blatt 4 oder so hatte er das auch das erste mal beschrieben)
    
    
}
