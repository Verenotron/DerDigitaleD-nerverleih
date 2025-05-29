package de.hsrm.mi.web.derdigitaledoenerverleih.ui.doener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.DoenerServiceImpl;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.doener.ZutatenServicImpl;


@Controller
public class DoenerController {

    @Autowired DoenerServiceImpl doenerService;
    @Autowired ZutatenServicImpl zutatenService;

    @ModelAttribute("doenerFormular")
    public DoenerFormular initFormular(){
        return new DoenerFormular();
    }

@GetMapping("/doener/liste")
public String getDoenerListe(Model model){
    List<Doener> alleDoener = new ArrayList<>(doenerService.findAllDoener());
    model.addAttribute("doenerListe", alleDoener);    
    return "doener/liste.html";
}

@PostMapping("/doener/{id}/del")
public String loescheDoener(@PathVariable("id") int id,
                            Model model){
    try{
        doenerService.deleteDoenerById(id);
    }catch(Exception e){
        model.addAttribute("info", "löschen hat nicht funktioniert");
    }
    List<Zutat> alleZutaten = new ArrayList<>(zutatenService.findAllZutaten());
    model.addAttribute("alleZutaten", alleZutaten);

    return "redirect:/doener/liste";
}

@GetMapping("/doener/0")
public String newDoener(@ModelAttribute("doenerFormular") DoenerFormular formular,
                        Model model){

    model.addAttribute("doenerFormular", formular);
    List<Zutat> alleZutaten = new ArrayList<>(zutatenService.findAllZutaten());
    model.addAttribute("alleZutaten", alleZutaten);

    return "doener/bearbeiten.html";
}

@GetMapping("/doener/{id}")
public String getDoerne(@PathVariable("id") long id, 
                        @ModelAttribute("doenerFormular") DoenerFormular formular, 
                        Model model){

    Doener datenbankDoener = new Doener();
    try{
        datenbankDoener = doenerService.findDoenerById(id).orElseThrow(() -> new DoenerException("Doener nicht gefunden"));
        formular = DoenerMapper.doenerToFormular(datenbankDoener);
        model.addAttribute("doenerFormular", formular);
        List<Zutat> alleZutaten = new ArrayList<>(zutatenService.findAllZutaten());
        model.addAttribute("alleZutaten", alleZutaten);
        return "doener/bearbeiten.html";
    }catch(DoenerException e){ //Wenn Döner nicht in DB, muss er neu angelegt werden
        model.addAttribute("doenerFormular", formular);
        return "doener/bearbeiten.html";
    }
}

@PostMapping("/doener/{id}")
public String speichereDoener(@ModelAttribute("doenerFormular") DoenerFormular formular,
                                @PathVariable("id") long id,
                                BindingResult result,
                                Model model){

    Doener datenbankDoener = new Doener();
    List<Zutat> alleZutaten = new ArrayList<>(zutatenService.findAllZutaten());

    model.addAttribute("alleZutaten", alleZutaten);
    if(result.hasErrors()){
        model.addAttribute("info", "Validierungsfehler");
        return "doener/bearbeiten.html";
    }
    formular.setVegetarizitaet(this.setZutatenVeganizität(formular.getZutaten()));
    if(id == 0){
        //! Repo sollte automatishc erkennen, ob id bereits vorhanden ist oder nicht. Falls id also null, kennt er diese nicht und generiert eine neue.
        datenbankDoener = DoenerMapper.formularToDoener(formular);
        try{
            datenbankDoener = doenerService.saveDoener(datenbankDoener);
        }catch(Exception e){
            model.addAttribute("info", e.getMessage());
        }
        formular = DoenerMapper.doenerToFormular(datenbankDoener);
        
        model.addAttribute("doenerFormular", formular);

        return "doener/bearbeiten.html";
    }else{
        try{
            Doener doener = DoenerMapper.formularToDoener(formular);
            // Zutat zutat = new Zutat();
            // try{
            //     zutat = zutatenService.findZutatById(formular.getZutaten().get(0).getEan()).orElseThrow(() -> new Exception("Geht nicht"));
            // }catch(Exception e){
            //     model.addAttribute("info", e.getMessage());
            // }
            
            // List<Zutat> echteZutaten = new ArrayList<>();
            // echteZutaten.add(zutat);
    
            // doener.setZutaten(echteZutaten);
            datenbankDoener = doenerService.saveDoener(doener);
            formular = DoenerMapper.doenerToFormular(datenbankDoener);
            
            model.addAttribute("doenerFormular", formular);

            return "doener/bearbeiten.html";
        }catch(DoenerException e){
            model.addAttribute("info", "Doener nicht gefunden.");
            return "doener/bearbeiten.html";
        }
    }
    

}

private int setZutatenVeganizität(List<Zutat> zutaten){
    int doenerVeganizitaet = 2;
    for(Zutat zutat : zutaten){
        if(doenerVeganizitaet > zutat.getVegetarizitaet()){
            doenerVeganizitaet = zutat.getVegetarizitaet();
        }
    }
    return doenerVeganizitaet;
}
    
}
