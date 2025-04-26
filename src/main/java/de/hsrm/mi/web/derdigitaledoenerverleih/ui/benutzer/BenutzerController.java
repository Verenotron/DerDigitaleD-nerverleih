package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;




@Controller
@SessionAttributes(names = {"benutzerMap"})
public class BenutzerController {

    @GetMapping("/benutzer/{loginName}")
    public String getBenutzer(@PathVariable("loginName") String name,
                                @ModelAttribute("formular") BenutzerFormular benutzerFormular,
                                Model model) {
        Map<String, BenutzerFormular> map;
        map = (Map<String, BenutzerFormular>) model.getAttribute("benutzerMap");
        if(map == null){
            map = new HashMap<>();
            model.addAttribute("name", name);
            return "benutzer/bearbeiten.html";
        }
        if(map.containsKey(name)){
            benutzerFormular = map.get(name);
            model.addAttribute("formular", benutzerFormular);
        }
        model.addAttribute("name", name);
        return "benutzer/bearbeiten.html";
    }

    @PostMapping("/benutzer/{loginName}")
    public String postMethodName(@PathVariable("loginName") String name,
                                    @ModelAttribute("formular") BenutzerFormular benutzerFormular,
                                    Model model
                                    ) {

        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("formular", benutzerFormular);
        Map<String, BenutzerFormular> map;
        // @SuppressWarnings("unckecked")
        map = (Map<String, BenutzerFormular>) model.getAttribute("benutzerMap"); //Model gibt immer ein Objekt zurück

        if(map == null){
            map = new HashMap<String, BenutzerFormular>();
        }

        map.put(benutzerFormular.getName(), benutzerFormular);

        model.addAttribute("benutzerMap", map);
        
        return "benutzer/bearbeiten.html";
    }
    
    
}
