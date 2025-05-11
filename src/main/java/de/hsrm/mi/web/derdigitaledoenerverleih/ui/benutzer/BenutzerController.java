package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes(names = {"formularMap"})
public class BenutzerController {

    @ModelAttribute("formularMap")
    public Map<String, BenutzerFormular> initMap(Model model){ //bei komkretem Rückgabetyp wird methode beim Sessionstart aufgerufen, mit void vor jedem Handler Aufruf
        return new HashMap<String, BenutzerFormular>();
    }

    Map<String, BenutzerFormular> sessionBenutzerMap;

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
    public String getBenutzer(@PathVariable("loginName") String name,
                                @ModelAttribute("formular") BenutzerFormular benutzerFormular, //sucht in Model -> Session -> Pfadvariablen oder erstellt neues Instanz
                                @ModelAttribute("formularMap") Map<String, BenutzerFormular> sessionBenutzerMap,
                               // Locale locale,
                                Model model) {
        
        //sessionBenutzerMap = (Map<String, BenutzerFormular>) model.getAttribute("formularMap");

       // model.addAttribute("sprache", locale.getDisplayLanguage());

        if(sessionBenutzerMap == null){
            sessionBenutzerMap = new HashMap<>();
            model.addAttribute("name", name);
            return "benutzer/bearbeiten.html";
        }
        if(sessionBenutzerMap.containsKey(name)){
            benutzerFormular = sessionBenutzerMap.get(name);
            model.addAttribute("formular", benutzerFormular);
        }
        model.addAttribute("name", name);
        return "benutzer/bearbeiten.html";
    }

    @PostMapping("/benutzer/{loginName}")
    public String postMethodName(@PathVariable("loginName") String name,
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
        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);
        // @SuppressWarnings("unckecked")
        //sessionBenutzerMap = (Map<String, BenutzerFormular>) model.getAttribute("formularMap"); //Model gibt immer ein Objekt zurück

        if(sessionBenutzerMap == null){
            sessionBenutzerMap = new HashMap<String, BenutzerFormular>();
        }

        sessionBenutzerMap.put(benutzerFormular.getName(), benutzerFormular);

        model.addAttribute("formularMap", sessionBenutzerMap);
        
        return "benutzer/bearbeiten.html";
    }
    
    
}
