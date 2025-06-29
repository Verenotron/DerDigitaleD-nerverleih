package de.hsrm.mi.web.derdigitaledoenerverleih.api.entleihung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.hsrm.mi.web.derdigitaledoenerverleih.api.doener.DoenerDTO;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.entleihung.EntleihungServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entleihung")
public class EntleihungsRestController {

    @Autowired EntleihungServiceImpl entleihungService;

    @PostMapping
    public void entleihen(@RequestBody EntleihungsDoenerDTO dto) {
        entleihungService.entleiheDoener(dto.id(), dto.benutzer());
    }

    @PostMapping("{loginName}/{doenerId}")
    public void entleiheDoenerFuerBenutzer(@PathVariable String loginName, @PathVariable long doenerId){
        entleihungService.zurueckgebeDoener(doenerId, loginName);
    }
    
    @GetMapping("{loginName}")
    public List<DoenerDTO> getEntlieheneDoener(@PathVariable String loginName){
        return entleihungService.findeEntleihungenVonBenutzer(loginName).stream().map(DoenerDTO::fromEntity).toList();
    }
    
}
