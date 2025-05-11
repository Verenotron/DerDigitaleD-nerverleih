package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer.BenutzerService;
import jakarta.validation.Valid;


@Controller
//@SessionAttributes(names = {"formularMap"})
//@SessionAttributes(names = {"formular"})
public class BenutzerController {

    //Map<String, BenutzerFormular> sessionBenutzerMap;
    @Autowired
    BenutzerService benutzerService;
    BenutzerMaper benutzerMapper = new BenutzerMaper();

    @ModelAttribute("formular")
    public BenutzerFormular initForm(Model model){ //bei komkretem Rückgabetyp wird methode beim Sessionstart aufgerufen, mit void vor jedem Handler Aufruf
        return new BenutzerFormular();
    }

    @GetMapping("/benutzer")
    public String getMethodName() {
        return "forward:/benutzer/neu";
    }

    // @GetMapping("/submit") //brauchen wir nur für den Sprachen wechsel, wenn ich erst auf submit und dann die sprache wechseln will
    // public String getSubmit(@RequestParam(name = "sprache", required = false) String sprache, //Parameter sprache ist optional, wenn er fehlt, wird er auf null gesetzt
    //                         Locale locale,
    //                         Model model) { //Model wird per DepedencyInjection initialisiert
    //     model.addAttribute("sprache", locale.getDisplayLanguage());
        
    //     return "benutzerbearbeiten";
    // }
    

    @GetMapping("/benutzer/{loginName}")
    public String getBenutzer(@PathVariable("loginName") String loginName,
                                @ModelAttribute("formular") BenutzerFormular benutzerFormular, //sucht in Model -> Session -> Pfadvariablen oder erstellt neues Instanz
                               // Locale locale,
                                Model model) {
        
        //sessionBenutzerMap = (Map<String, BenutzerFormular>) model.getAttribute("formularMap");

       // model.addAttribute("sprache", locale.getDisplayLanguage());

       Benutzer benutzer;
       try{
            benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new NoSuchElementException());
       }catch(NoSuchElementException e){
            model.addAttribute("name", loginName);
            return "benutzer/bearbeiten.html";
       }
       
        benutzerFormular = benutzerMapper.benutzerToBenutzerFormular(benutzer);
        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);

        return "benutzer/bearbeiten.html";
    }

    @PostMapping("/benutzer/{loginName}")
    public String postMethodName(@PathVariable("loginName") String loginName,
                                    @Valid @ModelAttribute("formular") BenutzerFormular benutzerFormular,
                                    BindingResult result,
                                    Model model
                                    ) {

        if(result.hasErrors()){
            model.addAttribute("info", "Daten bitte korrekt eingeben");
            return "benutzer/bearbeiten.html";
        }
        if(!benutzerFormular.getLosung().equals(benutzerFormular.getLosungWiederh())){
            result.rejectValue("losungWiederh", "Losungen müssen übereinstimmen", "Losungen müssen übereinstimmen");
            result.rejectValue("losung", "Losungen müssen übereinstimmen", "Losungen müssen übereinstimmen");
        }
        
        // @SuppressWarnings("unckecked")
        //sessionBenutzerMap = (Map<String, BenutzerFormular>) model.getAttribute("formularMap"); //Model gibt immer ein Objekt zurück

        Benutzer benutzer = benutzerMapper.benutzerFormularToBenutzer(benutzerFormular);
        if(benutzerService.findBenutzerById(loginName).isEmpty()){
            benutzer.setLoginName(loginName);
            benutzer = benutzerService.saveBenutzer(benutzer);
        }else{

        }

        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);
        
        return "benutzer/bearbeiten.html";
    }
    
    
}
