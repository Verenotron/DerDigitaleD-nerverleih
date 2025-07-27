package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer.Benutzer;
import de.hsrm.mi.web.derdigitaledoenerverleih.errorhandling.SeiteNichtVorhandenException;
import de.hsrm.mi.web.derdigitaledoenerverleih.services.benutzer.BenutzerService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@Controller // * Markiert Klasse als WebController-Komponente
//@SessionAttributes(names = {"formular"})
@RequestMapping("/benutzer")
public class BenutzerController {

    @Autowired
    BenutzerService benutzerService;

    @Autowired
    DispatcherServlet dispatcherServlet; //* Empfängt Anfragen und leitet sie an passenden Controller weiter

    @PostConstruct // * Markiert Methode, die direkt nach erstellung der Beand ausgeführt wird.
    public void printDispatcherSerlet(){
        System.out.println("DispatcherServlet: " + dispatcherServlet);
    }

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void printAllBeans(){
        String[] allBeans = applicationContext.getBeanDefinitionNames();
        System.out.println("Beans im ApplicationContext: ");
        for(String bean : allBeans){
            System.out.println(bean);
        }
    }

    BenutzerMaper benutzerMapper = new BenutzerMaper();

    @ModelAttribute("formular")
    public BenutzerFormular initForm(Model model){ //bei komkretem Rückgabetyp wird methode beim Sessionstart aufgerufen, mit void vor jedem Handler Aufruf
        return new BenutzerFormular();
    }

    @GetMapping("test")
    public String getError404(){
        throw new SeiteNichtVorhandenException("Seite nicht vorhanden");
    }

    @GetMapping()
    public String getMethodName(HttpServletRequest request) {
        System.out.println("Request: " + request);
        return "benutzer/liste.html";
        // return "forward:/benutzer/neu";
    }

    @GetMapping("/{loginName}")
    public String getBenutzer(@PathVariable("loginName") String loginName,
                                @ModelAttribute("formular") BenutzerFormular benutzerFormular, // ! sucht in Model -> Session -> Pfadvariablen oder erstellt neues Instanz
                               // Locale locale,
                                Model model) {

       Benutzer benutzer;
       try{
            benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new BenutzerException("Benutzer nicht gefunden"));
       }catch(BenutzerException e){
            //model.addAttribute("info", e.getMessage());
            model.addAttribute("name", loginName);
            return "benutzer/bearbeiten.html";
       }
       
        benutzerFormular = benutzerMapper.benutzerToBenutzerFormular(benutzer);
        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);

        return "benutzer/bearbeiten.html";
    }

    // @GetMapping("/benutzer/neu")
    // public String erstelleNeuenBenutzer(){
    //     return "forward:/benutzer/testName";
    // }

    @GetMapping("liste")
    public String getMethodName(Model model) {
        List<Benutzer> alleBenutzer = new LinkedList<Benutzer>(benutzerService.findAllBenutzer());
        alleBenutzer.sort((a, b) -> (a.getName().compareTo(b.getName())));
        model.addAttribute("benutzerListe", alleBenutzer);
        
        return "benutzer/liste.html";
    }
    @PostMapping("/{name}/del")
    public String loescheBenutzer(@PathVariable("name") String name){
        benutzerService.deleteBenutzerById(name);
        return "redirect:/benutzer/liste";
    }

    @PostMapping("/{loginName}")
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

        Benutzer benutzer = benutzerMapper.benutzerFormularToBenutzer(benutzerFormular);

            try{
                benutzer.setLoginName(loginName);
                benutzer = benutzerService.saveBenutzer(benutzer);
            }catch(BenutzerException e){
                model.addAttribute("info", e.getMessage());
                try{
                    benutzer = benutzerService.findBenutzerById(loginName).orElseThrow(() -> new BenutzerException("Benutzer konnte nicht gefunden werden"));
                }catch(Exception f){
                    model.addAttribute("info", f.getMessage());
                    benutzerFormular = benutzerMapper.benutzerToBenutzerFormular(benutzer); //Damit alte daten übernommen werden und nciht die daten die versucht wurden zu speichern
                }
            }
            
       // }
        //benutzerFormular = benutzerMapper.benutzerToBenutzerFormular(benutzer);

        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);
        
        return "benutzer/bearbeiten.html";
    }

    @GetMapping(value="/suche", params="loginname")// ! params optional
    public String sucheName(
        @RequestParam(name = "loginname", required=true) String name, // ! Filtert Query Parameter ?loginname=... . Automatisches Konvertieren zum angegebenen Datentyp
        Model m
        ){
            Benutzer benutzer = benutzerService.findBenutzerById(name).orElseThrow(() -> new RuntimeException());
            BenutzerFormular benutzerFormular = benutzerMapper.benutzerToBenutzerFormular(benutzer);
            m.addAttribute("formular", benutzerFormular);

            return "benutzer/bearbeiten.html";
    }   
    
    
}
