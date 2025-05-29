package de.hsrm.mi.web.derdigitaledoenerverleih.api.doener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.DoenerServiceImpl;

@RestController
public class DoenerRestController {

    @Autowired DoenerServiceImpl doenerservice;

    @GetMapping("/api/doener/{id}")
    public ResponseEntity<DoenerDTO> getDoener(@PathVariable("id") int id){
        return doenerservice.findDoenerById(id).map(doener -> ResponseEntity.ok(DoenerDTO.fromEntity(doener))).orElseGet(() -> ResponseEntity.status(404).build()) ;
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

        return alleDTO;
    }
    
    
}
